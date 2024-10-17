import requests
from bs4 import BeautifulSoup
import re
import random
from urllib.request import urlopen, Request

pages = ["C:\\Users\\joaop\\Desktop\\scrapeZara\\TShirtsEstampadasHomem.html",
         "C:\\Users\\joaop\\Desktop\\scrapeZara\\CalcasHomem.html",
         "C:\\Users\\joaop\\Desktop\\scrapeZara\\SweaterEPolosHomem.html",
         "C:\\Users\\joaop\\Desktop\\scrapeZara\\CasacosAlmofadadosHomem.html",
         "C:\\Users\\joaop\\Desktop\\scrapeZara\\BlazersHomem.html",
         "C:\\Users\\joaop\\Desktop\\scrapeZara\\VestidoMulher.html",
         "C:\\Users\\joaop\\Desktop\\scrapeZara\\TopsMulher.html",
         "C:\\Users\\joaop\\Desktop\\scrapeZara\\CalcasMulher.html",
         "C:\\Users\\joaop\\Desktop\\scrapeZara\\CasacoBlusaoMulher.html",
         "C:\\Users\\joaop\\Desktop\\scrapeZara\\SaiaMulher.html",
         "C:\\Users\\joaop\\Desktop\\scrapeZara\\TShirtsMulher.html",
         "C:\\Users\\joaop\\Desktop\\scrapeZara\\CasacosCriancas.html",
         "C:\\Users\\joaop\\Desktop\\scrapeZara\\TShirtCrianca.html",
         "C:\\Users\\joaop\\Desktop\\scrapeZara\\CalcasCrianca.html",
         "C:\\Users\\joaop\\Desktop\\scrapeZara\\CalcadoHomem.html",
         "C:\\Users\\joaop\\Desktop\\scrapeZara\\CalcadoMulher.html",
         "C:\\Users\\joaop\\Desktop\\scrapeZara\\CalcadoCrianca.html",
         "C:\\Users\\joaop\\Desktop\\scrapeZara\\conjuntos.html"
         ]

id = 0
peca =[]
setc =[]
set_peca =[]

citem = f"""INSERT INTO item (iditem, codigo, cor, designacao, disponibilidade, estilo, imagem, nr_disponiveis, nraquisicoes, preco, tipo, lojaidloja)
VALUES\n"""
cset = f"""\n\nINSERT INTO set (nr_pecas, tamanho, itemiditem)
VALUES\n"""
csetpeca = f"""\n\nINSERT INTO set_peca (peca_itemiditem, set_itemiditem)
VALUES\n"""
cpeca = f"""\n\nINSERT INTO peca (tamanho, itemiditem)
VALUES\n"""
ccalcado = f"""\n\nINSERT INTO calcado (numero, itemiditem)
VALUES\n"""


texto=""
conjuntoOn=False
firstConjunto=-1
for page in pages:
    f = open(page, encoding='utf-8')
    soup = BeautifulSoup(f, 'html.parser')
    print("scraping page "+str(page))

    #body = soup.html.body.find("div",{"id":"app-root"}).find("div",{"class":"theme"}).find("div",{"class":"theme__app"}).find("div")
    #grid = body.find("div",class_=re.compile(r"layout--grid-type-standard")).find("div",class_=re.compile(r"layout-catalog-content--full")).find("main",{"class":"layout-content__main"})
    #article = grid.find("article",{"class":"layout-content__article"})
    #product_groups = article.find("div",{"class":"product-groups"})
    #section = product_groups.find("section",class_=re.compile(r"product-grid--is-zoom2"))
    ul = soup.find("ul",{"class":"product-grid__product-list"})

    products = ul.find_all("li",class_=re.compile(r"column"))

    disponibilidade="In Stock"
    tipo="Male"

    if(str(page).__contains__("Mulher")):
        tipo="Female"
    if(str(page).__contains__("Crianca")):
        tipo="Child"
    
    estilos = ["Casual", "Clássico", "Esportivo", "Boho", "Minimalista", "Vintage", "Grunge", "Streetwear", "Romântico", "Punk"]
    tamanhos = ["S","M","L","XL"]
    items = []

    for p in products:
        id+=1
        img = p.find("div",{"class":"product-grid-product__figure"}).find("a",{"class":"product-link product-grid-product__link link"}).find("div",{"class":"products-category-grid-media-container"}).find("div",{"class":"media products-category-grid-media"}).find("div",class_=re.compile("media__wrapper")).find("img")
        imagem = img.attrs['src']
        cor = img.attrs['alt'].split(" - ")[1].split(" ")[0]
        data = p.find("div",{"class":"product-grid-product__data"}).find("div",{"class":"product-grid-product__info-wrapper"}).find("div",{"class":"product-grid-product-info"}).find("div",class_=re.compile(r"product-grid-product-info__product-header"))
        nome = data.find("div",{"class":"product-grid-product-info__main-info"}).find("a",class_=re.compile(r"product-link"))
        estilo = estilos[random.randint(0,len(estilos)-1)]
        tamanho = tamanhos[random.randint(0,len(tamanhos)-1)]

        if(nome!=None):
            nome = nome.find("h2").get_text(strip=True)

        if(data.find("div",class_=re.compile(r"product-grid-product-info__product-price"))!=None):
            price_info = data.find("div",class_=re.compile(r"product-grid-product-info__product-price")).find("span",{"class":"price__amount-wrapper"}).find("span",{"class":"price__amount"})
            if(price_info.find("span",{"class":"price-current__amount"})==None):
                id-=1
                continue

            preco = price_info.find("span",{"class":"price-current__amount"}).find("div",{"class":"price-formatted__price-amount"}).find("span").get_text(strip=True).split(" ")[0].replace(",",".")
        else:
            preco = random.randint(10,25)


        if(not(str(nome).__contains__("CONJUNTO")) and not(str(nome).__contains__("LOOK"))):
            cpeca+="('"+str(tamanho)+"',"+str(id)+"),\n"
        else:
            cset+="(2,'"+str(tamanho)+"',"+str(id)+"),\n"
            if(conjuntoOn==False):
                firstConjunto=id
                conjuntoOn=True

        if(str(page).__contains__("Calcado") and str(page).__contains__("Mulher")):
            ccalcado+="("+str(random.randint(35,40))+","+str(id)+"),\n"
        if(str(page).__contains__("Calcado") and str(page).__contains__("Homem")):
            ccalcado+="("+str(random.randint(37,44))+","+str(id)+"),\n"
        if(str(page).__contains__("Calcado") and str(page).__contains__("Crianca")):
            ccalcado+="("+str(random.randint(25,35))+","+str(id)+"),\n"


        nr_disponiveis=random.randint(1,3)
        codigo="P"+str(id)
        item=f"({id},'{codigo}','{cor}','{nome}','{disponibilidade}','{estilo}','{imagem}',{nr_disponiveis},0,{preco},'{tipo}',1)"
        items.append(item)

    for i in items:    
        texto +=i+",\n" 

    f.close()

fp = open("catalogo.sql", "w",encoding='utf-8')

citem+=texto
citem = citem[:-2]
citem += ';'

cpeca = cpeca[:-2]
cpeca += ';'

cset = cset[:-2]
cset += ';'

for i in range(0,int((id-firstConjunto+1)/3.0)):
    csetpeca+="("+str(firstConjunto+1)+","+str(firstConjunto)+"),\n"
    csetpeca+="("+str(firstConjunto+2)+","+str(firstConjunto)+"),\n"
    firstConjunto+=3

csetpeca = csetpeca[:-2]
csetpeca += ';'

ccalcado = ccalcado[:-2]
ccalcado += ';'

loja = f"""INSERT INTO loja (idloja, localizacao, marca, nome)
VALUES
(1, 'Braga', 'Independente', 'Os Criadores');\n\n"""

fp.write(loja)
fp.write(citem)
fp.write(cpeca)
fp.write(cset)
fp.write(csetpeca)
fp.write(ccalcado)

updateIds= f"""\n\nSELECT setval('"catalogoservice_item_iditem_seq"', (SELECT MAX(iditem) FROM item));\n\n
SELECT setval('"catalogoservice_loja_idloja_seq"', (SELECT MAX(idloja) from loja));"""
fp.write(updateIds)

fp.close()

