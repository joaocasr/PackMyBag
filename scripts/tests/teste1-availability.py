import threading
import requests

def checkAvailability(data):
    try:

        r = requests.post('http://localhost:8081/api/catalogo/disponibilidade/', json=data)
        print(f"Thread {threading.current_thread().name} - Status: {r.status_code}, Response: {r.text}")
    except requests.exceptions.RequestException as e:
        print(f"Error: {e}")

if __name__ == "__main__":

    order1 = {
        "itens": [
            {"codigo": "P48", "idloja": 1, "quantidade": 1},
            {"codigo": "P51", "idloja": 1, "quantidade": 1}
        ]
    }
    
    order2 = {
        "itens": [
            {"codigo": "P48", "idloja": 1, "quantidade": 2},
            {"codigo": "P53", "idloja": 1, "quantidade": 1}
        ]
    }

    threads = []
    t1 = threading.Thread(target=checkAvailability, args=(order1,))
    t2 = threading.Thread(target=checkAvailability, args=(order2,))
    
    t1.start()
    t2.start()

    t1.join()
    t2.join()
