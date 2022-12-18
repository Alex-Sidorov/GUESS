## Технический долг
#### Признаки(нейронная сеть):
1. **Наличие непонятного/нечитабельного кода.**
Запутанная логика запуска нейронной сети

2. **Дублирующийся код**.
Отсутствует. Был разрешен во время рефакторинга

3. **Отсутствие автоматизации**.
Автоматизация сборки и развертки

4. **Запутанная архитектура и ненужные сложные зависимости**.
Отсутствует

5. **Медленные / неэффективные средства**.
Медленная работа без GPU

6. **Незакоммиченный код / долгоживущие ветки**.
Незакоммиченного кода не обнаружено, как и лишних веток

7. **Отсутствие / несоответствие технической документации**.
Несоответствие в точности нейронной сети

8. **Отсутствие тестовой среды**.
Отсутсвие CI для тестов

#### Признаки(GUI):
1. **Наличие непонятного/нечитабельного кода**.
Данный пункт представлен отсутствием разделения графического интерфейса и логики, что создает трудности при добавлении нового кода

2. **Дублирующийся код**.
Наличие нескольких участков кода, выполняющих одну функциональность выражено добавлением новых возможностей и проблем связанных с этим

3. **Отсутствие автоматизации**.
Поскольку большая часть функциональности связана с интерфейсом пользователя, то её требуется проверять "вручную".

4. **Запутанная архитектура и ненужные сложные зависимости**.
Предствлено пунктом 1

5. **Медленные / неэффективные средства**.
Явных не обнаружено

6. **Незакоммиченный код / долгоживущие ветки**.
Незакоммиченного кода не обнаружено, как и лишних веток

7. **Отсутствие / несоответствие технической документации**.
Несоответсвие выражены тем, что проект находится в стадии доработки

8. **Отсутствие тестовой среды**.
Предствлено пунктом 3

#### Признаки(Backend):
1. **Наличие непонятного/нечитабельного кода**.
Данный пункт обусловлен присутствием SQL-кода в интерфейсах репозиториев.

2. **Дублирующийся код**.
Наличие нескольких участков кода в сервисной логике API.

3. **Отсутствие автоматизации**.
Отсутствует автоматизация развертки приложения на облачной платформе.

4. **Запутанная архитектура и ненужные сложные зависимости**.
Не обнаружено.

5. **Медленные / неэффективные средства**.
Использование ORM Hibernate вместо стандартных методов обращения к базе данных.

6. **Незакоммиченный код / долгоживущие ветки**.
Незакоммиченного кода не обнаружено, как и лишних веток.

7. **Отсутствие / несоответствие технической документации**.
Несоответсвие выражены тем, что проект находится в стадии доработки.

8. **Отсутствие тестовой среды**.
Недостаточное покрытие интеграционными тестами.

### План мероприятий:
1. Повышение качества нейронной сети.
2. Переработка интерфейса (см. Признаки(GUI) пункт 1).
3. Удаление потенциально ненужного кода (см. Признаки(GUI) пункт 2).
4. Разделение логики и интерфейса.
5. Написание интеграционных тестов (см. Признаки(Backend) пункт 8).
6. Добавление автоматизации развертки и тестрования API (см. Признаки(Backend) пункт 3).

### Оценка:
В результате анализа проекта на наличие технического долга и составления плана мероприятий, можно сделать вывод, что объем невыполненной функциональности превышает объем технического долга. Таким образом следует сконцетрировать внимание на выполнении функциональности.

### Выводы:
* Объем невыполненной функциональности превышает объем технического долга, из чего следует сконцетрировать внимание на первом.
* Устранение технического долга будет выражено в повышении качества нейронной сети, переработке интерфейса, удалении потенциально ненужного кода, разделение логики и интерфейса, написание интеграционных тестов, добавление автоматизации развертки и тестрования API, так как это затрудняет разработку.
* Из-за отсутствия многой функциональности демонстрацию законченного продукта требуется перенести(см. Trello)