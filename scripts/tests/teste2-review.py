import threading
import requests

def doReview(data):
    try:

        r = requests.post('http://localhost:8081/api/catalogo/items/398/addreview', json=data)
        print(f"Thread {threading.current_thread().name} - Status: {r.status_code}, Response: {r.text}")
    except requests.exceptions.RequestException as e:
        print(f"Error: {e}")

if __name__ == "__main__":

    review1 = {
        "username":"john22",
        "name":"johnnylonny",
        "profileImg":"xxx",
        "texto":"cool piece",
        "timestamp":"17-10-2024 21:40",
        "rating":3
    }   
    
    review2 = {
        "username":"alej22",
        "name":"johnnylonny",
        "profileImg":"xxx",
        "texto":"cool piece",
        "timestamp":"17-10-2024 21:40",
        "rating":3
    }   


    threads = []
    t1 = threading.Thread(target=doReview, args=(review1,))
    t2 = threading.Thread(target=doReview, args=(review2,))
    
    t1.start()
    t2.start()

    t1.join()
    t2.join()
