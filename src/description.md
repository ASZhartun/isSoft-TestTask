# TestTask of ISSoft.by
##Description
В файле [orders.csv](https://github.com/ASZhartun/isSoft-TestTask/src/main/resources/order_items.csv) - хранятся 
данные заказов (ID - id заказа, DATE_TIME - дата заказа).  
В файле [order_items.csv](https://github.com/ASZhartun/isSoft-TestTask/src/main/resources/order_items.csv) - хранятся 
данные заказанных позиций (ORDER_ID - id заказа, PRODUCT_ID - id товара, QUANTITY - количество товара в заказе).  
В файле [products.csv](https://github.com/ASZhartun/isSoft-TestTask/src/main/resources/order_items.csv) - данные 
товаров (ID - id товара, NAME - название товара, PRICE_PER_UNIT - цена за единицу товара).
##Goals
Необходимо, за каждый день месяца определить товар, принёсший максимальную прибыль. 
В качестве ответа вывести NAME товара лидера за дату ***2021-01-21***.
##Answer
service.Engine.class -> psvm. 