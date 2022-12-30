# Linebot - IP 查詢

**此 Repository 僅為展示用。**

整體要達到的目的為：可以在官方帳號上查詢梅樹派的浮動 IP 位置

專案分為兩個部分：
1. 樹梅派上的排程：使用 Groovy 撰寫，查詢當下 IP 用
2. Heroku 上的 Linebot 服務：有一個接收 IP 資料的 IP 給樹梅派打，還有串接 Line 的服務。

使用流程為：
1. 樹梅派排程查詢 IP 後，打 Heroku 上的 API 去傳送紀錄
2. 使用官方帳號，於聊天室中輸入關鍵字，即可得到回應該 IP 位置資訊。