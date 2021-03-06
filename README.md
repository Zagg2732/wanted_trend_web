WANTED_TREND_WEB
===
***

## ๐ธ Site Screenshot
![main](readme_pictures/screenshot_main.png)

## ๐ Link
- **๋ฐฐํฌ ์ฌ์ดํธ :** http://wantedtrend.site &ensp; <img src="https://img.shields.io/website?url=http://wantedtrend.site/" />
- **ํ์ด์ฌ ํฌ๋กค๋ง :** https://github.com/Zagg2732/wanted_trend


* * *
# Introduce Project
* * *
์ด ํ๋ก์ ํธ๋ 1์ธ ํ๋ก์ ํธ ์๋๋ค.

- ์ฑ์ฉ ํ๋ซํผ [Wanted](https://wanted.co.kr/) ์ ๊ณต๊ณ ๋ค์ ํตํด ๋ํ๋ฏผ๊ตญ ๊ธฐ์๋ค์ด ์ํ๋ ํ๋ก๊ทธ๋๋ฐ ์ธ์ด ์ถ์ธ๋ฅผ ํฌ๋กค๋ง ํ์ต๋๋ค([WANTED_TREND](https://github.com/Zagg2732/wanted_trend/))

- ํฌ๋กค๋งํ ๋ฐ์ดํฐ๋ฅผ ๋ถ์ํ์ฌ ์ป์ ์ธ์ฌ์ดํธ๋ฅผ ํ๋์ ์์์๊ฒ ์ฐจํธ๋ก ํํํ์ต๋๋ค

- SpringBoot Schedular๋ฅผ ํตํด ๋งค์ผ ์ํ๋ ์๊ฐ์ ํฌ๋กค๋งํ์ฌ ๋ฐ์ดํฐ๊ฐ ๊ฐฑ์ ๋๊ฒ๋ ๊ตฌํํ์ต๋๋ค

- AWS ์๋ฒ์ ๋ฐฐํฌ๋์์ต๋๋ค [์ฌ์ดํธ](http://wantedtrend.site)


## BACKEND

1. SpringBoot Scheduling ์ ํ์ฉํ์ฌ ์ํ๋ ์๊ฐ์ Java ์ฝ๋๋ก python compiler๋ฅผ ์คํํฉ๋๋ค.
2. ํ์ด์ฌ์ requests๋ฅผ ํ์ฉํ์ฌ ์ํฐ๋ ์ฌ์ดํธ์ ์น์ ๋ณด๋ฅผ ๋ฐ์์ค๊ณ , openpyxl์ ํ์ฉํ์ฌ ์ํ๋ ์ ๋ณด๋ฅผ excel ํํ๋ก ์ ์ฅํฉ๋๋ค.
3. ํฌ๋กค๋ง ๋ฐ ์์ ์ ์ฅ์ด ์๋ฃ๋๋ฉด Java์์ ํด๋น ํ์ผ์ ์ฝ์ด์ค๊ณ , JPA๋ฅผ ํ์ฉํ์ฌ AWS Cloud DB(MariaDB) ์ ์ ์ฅํฉ๋๋ค.
4. JPA๋ฅผ ํ์ฉํ์ฌ DB๋ฅผ ๋ถ์ํ๊ณ  ์ํ๋ ์ ๋ณด๋ฅผ Java enum class ๋ฅผ ํ์ฉํ์ฌ ํฌ๋กค๋ง ์ธ์ด์ ์ฐจํธ์์ ํํํ  ์์์ ๋งค์นํฉ๋๋ค.
5. Json ํ์ผ๋ก ์ ์ฅํฉ๋๋ค.

## FRONTEND

- Vue.js project๋ก ๊ตฌ์ฑ๋์์ต๋๋ค.
- ํ๋ฉด ๋์์ธ์ Bootstrap5 ํํ๋ฆฟ์ ์ปค์คํํ์์ต๋๋ค.
- Vue.js ์ Lifecycle์ ํ์ฉํ์ฌ restful api ํตํด Json ๋ฐ์ดํฐ๋ฅผ ๋ฐ์์ต๋๋ค.
- Apexchart ๋ผ์ด๋ธ๋ฌ๋ฆฌ์ json ๋ฐ์ดํฐ๋ฅผ ์ฐ๋ํด ๋ฐ์ดํฐ๋ฅผ ํํํฉ๋๋ค.


## ๐  Stack
### Front-End
- Vue.js 3 
- Vue-Router 4
- Vue-axios 
- Bootstrap5
- Apexchart

### Back-End
- Java 1.8
- Gradle 
- Spring Boot
- JPA

### DataBase
- MariaDB (AWS)

### Server
- AWS free-tier (Amazon EC2 - AWS linux os)



## ๐ Features
- Vue.js๋ฅผ ํ์ฉํ SPA ๋ฐฉ์์ ์ฌ์ดํธ ๊ตฌํ
- Bootstrap 5.0.2 ๋ฅผ ํ์ฉํ ์ฌ์ดํธ ๋์์ธ ๋ฐ modal ๊ธฐ๋ฅ ๊ตฌํ
- JPA ๋ฅผ ํ์ฉํ DB ๊ฐ๋ฐ
- Restful api ๊ตฌํ
- npm ์ ํ์ฉํ ํ์ด์ง์ฒ๋ฆฌ
- Gradle ๋น๋ ๋๊ตฌ ์ฌ์ฉ
- ๊ฒ์๊ธ, ๋๊ธ์ ๊ด๋ จํ ๊ธฐ๋ณธ์ ์ธ CRUD ๊ตฌํ
- ๊ฒ์๊ธ ํ์ผ ๋ฏธ๋ฆฌ๋ณด๊ธฐ, ์๋ก๋, ๋ค์ด๋ก๋ ๊ตฌํ

## ๐ฟ Installation
```
# required
- Java 1.8
- Node.js
- Vue 3.x.x
- Python 3.x.x

# clone repo
git clone https://github.com/Zagg2732/wanted_trend_web.git
```

### `application.properties` File settings
>> src/main/resources/```application.proterties```
>   ~~~
> spring.devtools.livereload.enabled=true
> spring.jpa.show-sql=true (true: jpa log console)
>
> server.port = ์๋ฒ ํฌํธ(default : 8080)
>
> # none for test
> spring.jpa.hibernate.ddl-auto= validate
> logging.level.org.hibernate.type=trace
>
> spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
>
> spring.datasource.url=jdbc:mariadb://db์ฃผ์:ํฌํธ/ํ์ด๋ธ์ด๋ฆ
> spring.datasource.username=์์ด๋
> spring.datasource.password=๋น๋ฐ๋ฒํธ
>
> # project custom path
> path.refresh.wanted_json=๋งค์ผ ๋ณ๊ฒฝ๋๋ json ํด๋
> path.logging.wanted_json=json๋ค์ ๋งค์ผ ์ ์ฅํ  ํด๋(logging)
> path.load.wanted_excel=excel file์ด ์ ์ฅ๋ ํด๋
>
> # python path (wanted_trend ํ๋ก์ ํธ ์ฐ๋)
> path.load.python_scripts=ํ์ด์ฌ์คํฌ๋ฆฝํธ(python.exe or python3) ์ ๋๊ฒฝ๋ก
> path.load.python_main=ํ์ด์  main.py ์ ๋๊ฒฝ๋ก
>
> python command {begin : ์ฒ์์์ or ํน์  ๊ฐ์ ํฌ๋กค๋ง์, daily : ๋งค์ผ์คํ, ์ด์ ~์ต์  ํฌ๋กค๋ง}
> path.load.python_crawl_command=begin ํน์ daily
>   ~~~
