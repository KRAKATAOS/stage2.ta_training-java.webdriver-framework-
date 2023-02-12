# stage2.ta_training-java.webdriver-framework-

-framework(test)

task description: Задача - построить фреймворк для автоматизации Hardcore задания из курса WebDriver.

Что должно быть в итоговом фреймворке:

webdrivermanager для управления коннекторам к браузерам Page Object / Page Factory для абстракций страниц Модель для бизнес-объектов необходимых сущностей properties файлы с тестовыми данным для разных окружений (как минимум 2) xml suites для smoke тестов и всех тестов При падении теста должен быть сделан скриншот с датой и временем Фреймворк должен иметь возможность запуска с Jenkins и параметризацией браузера, тест suite, environment. Результаты тестов должны быть на графике джобы, скриншоты должны быть заархивированны как артефакты

notes to "framework" task-> parameters to start mvn project: -Dbrowser={param1} -Denvironmnet={param2} -Dsurefire.suiteXmlFiles=src\test\resources\testng-all.xml

param1: chrome | firefox | edge param2: qa | dev

example: mvn -Dbrowser=chrome -Denvironment=qa -Dsurefire.suiteXmlFiles=src\test\resources\testng-all.xml clean test
