# Jade-Average

Реализация алгоритма вычисления среднего значения агентов с
использованием [Java Agent DEvelopment Framework](https://jade.tilab.com). Данный проект является практическим заданием курса
мультиагентных технологий в СПБГУ.

## Алгоритм

Рассматривается произвольная связная топология. Каждый агент имеет сведения о своём числе и соседних агентах. В первый
такт каждый агент отправляет известные ему значения соседям (`SendValuesOneShotBehaviour`). Затем агенты ждут входящих
сообщений с информацией о значениях других агентов (`ReceiveNeighborsValuesBehaviour`). Цикл обмена значениями
продолжается, пока в каждом агенте не соберется информация со всех агентов. После этого каждый агент подсчитывает
среднее значение (`CalculateAverageOneShotBehaviour`).

Граф задаётся как список смежности в static-блоке `src/main/java/App.java:16`

### Оценки

В худшем случае бамбука требуется n тактов, чтобы собрать в каждом агенте информацию со всего графа.
Тогда количество коммуникаций равно `O(n*n)`.
Так как в каждом агенте хранятся значения всех других агентов, используемая память оценивается `O(n*n)`.

|Память|Сообщения между агентами|Сообщения в центр|Тактов|
|---|---|---|---|
|`O(n*n)`|`O(n*n)`|`n`|`O(n)`|

#### Количество операций в каждом агенте

|Сложение|Вычитание|Умножение|Деление|
|---|---|---|---|
|`n`|`0`|`0`|`1`|

## Запуск

### Требования
* Java SDK
* Gradle
* Intellij IDEA
* jade.jar

1. Создать папку `libs` в корне проекта и поместить в неё `jade.jar`
2. Импортировать проект в Intellij IDEA
3. Запустить `main` в `src/main/java/App.java`

### Пример запуска

```text
average-agent-2 agent creating
average-agent-1 agent creating
average-agent-3 agent creating
average-agent-1 got value = 59
average-agent-2 got value = 50
average-agent-1 got number of agents = 5
average-agent-2 got number of agents = 5
average-agent-1 got neighbors = [average-agent-2, average-agent-3, average-agent-0]
average-agent-2 got neighbors = [average-agent-3, average-agent-1]
average-agent-3 got value = 20
average-agent-3 got number of agents = 5
average-agent-3 got neighbors = [average-agent-4, average-agent-2, average-agent-0, average-agent-1]
average-agent-1 agent created
average-agent-3 agent created
average-agent-2 agent created
average-agent-0 agent creating
average-agent-1 agent sent inform message with content = average-agent-1 59 
average-agent-2 agent sent inform message with content = average-agent-2 50 
average-agent-3 agent sent inform message with content = average-agent-3 20 
average-agent-4 agent creating
average-agent-4 got value = 5
average-agent-4 got number of agents = 5
average-agent-4 got neighbors = [average-agent-3]
average-agent-4 agent created
average-agent-4 agent sent inform message with content = average-agent-4 5 
average-agent-0 got value = 78
average-agent-0 got number of agents = 5
average-agent-0 got neighbors = [average-agent-3, average-agent-1]
average-agent-0 agent created
average-agent-0 agent sent inform message with content = average-agent-0 78 
average-agent-1 agent received message with content = average-agent-2 50 
average-agent-2 agent received message with content = average-agent-1 59 
average-agent-3 agent received message with content = average-agent-2 50 
average-agent-4 agent received message with content = average-agent-3 20 
average-agent-0 agent received message with content = average-agent-3 20 
average-agent-1 agent received message with content = average-agent-3 20 
average-agent-3 agent received message with content = average-agent-1 59 
average-agent-2 agent received message with content = average-agent-3 20 
average-agent-0 agent received message with content = average-agent-1 59 
average-agent-3 agent sent inform message with content = average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-4 agent sent inform message with content = average-agent-4 5 average-agent-3 20 
average-agent-2 agent sent inform message with content = average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-1 agent sent inform message with content = average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-0 agent sent inform message with content = average-agent-3 20 average-agent-0 78 average-agent-1 59 
average-agent-3 agent sent inform message with content = average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-4 agent received message with content = average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-2 agent sent inform message with content = average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-0 agent sent inform message with content = average-agent-3 20 average-agent-0 78 average-agent-1 59 
average-agent-1 agent sent inform message with content = average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-3 agent received message with content = average-agent-4 5 
average-agent-4 agent received message with content = average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-2 agent received message with content = average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-0 agent received message with content = average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-3 agent received message with content = average-agent-0 78 
average-agent-1 agent received message with content = average-agent-0 78 
average-agent-4 agent sent inform message with content = average-agent-4 5 average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-2 agent received message with content = average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-0 agent received message with content = average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-1 agent received message with content = average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-3 agent sent inform message with content = average-agent-4 5 average-agent-2 50 average-agent-3 20 average-agent-0 78 average-agent-1 59 
average-agent-4 agent sent inform message with content = average-agent-4 5 average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-0 agent sent inform message with content = average-agent-2 50 average-agent-3 20 average-agent-0 78 average-agent-1 59 
average-agent-2 agent sent inform message with content = average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-1 agent sent inform message with content = average-agent-2 50 average-agent-3 20 average-agent-0 78 average-agent-1 59 
average-agent-4 agent received message with content = average-agent-4 5 average-agent-2 50 average-agent-3 20 average-agent-0 78 average-agent-1 59 
average-agent-3 agent calculated average = 42.4
average-agent-2 agent sent inform message with content = average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-0 agent sent inform message with content = average-agent-2 50 average-agent-3 20 average-agent-0 78 average-agent-1 59 
average-agent-1 agent sent inform message with content = average-agent-2 50 average-agent-3 20 average-agent-0 78 average-agent-1 59 
average-agent-4 agent calculated average = 42.4
average-agent-2 agent received message with content = average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-0 agent received message with content = average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-1 agent received message with content = average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-2 agent received message with content = average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-0 agent received message with content = average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-1 agent received message with content = average-agent-3 20 average-agent-0 78 average-agent-1 59 
average-agent-2 agent sent inform message with content = average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-0 agent sent inform message with content = average-agent-2 50 average-agent-3 20 average-agent-0 78 average-agent-1 59 
average-agent-1 agent sent inform message with content = average-agent-2 50 average-agent-3 20 average-agent-0 78 average-agent-1 59 
average-agent-2 agent sent inform message with content = average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-1 agent sent inform message with content = average-agent-2 50 average-agent-3 20 average-agent-0 78 average-agent-1 59 
average-agent-0 agent sent inform message with content = average-agent-2 50 average-agent-3 20 average-agent-0 78 average-agent-1 59 
average-agent-2 agent received message with content = average-agent-4 5 average-agent-2 50 average-agent-3 20 average-agent-0 78 average-agent-1 59 
average-agent-1 agent received message with content = average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-0 agent received message with content = average-agent-4 5 average-agent-2 50 average-agent-3 20 average-agent-0 78 average-agent-1 59 
average-agent-2 agent calculated average = 42.4
average-agent-1 agent received message with content = average-agent-2 50 average-agent-3 20 average-agent-1 59 
average-agent-0 agent calculated average = 42.4
average-agent-1 agent sent inform message with content = average-agent-2 50 average-agent-3 20 average-agent-0 78 average-agent-1 59 
average-agent-1 agent sent inform message with content = average-agent-2 50 average-agent-3 20 average-agent-0 78 average-agent-1 59 
average-agent-1 agent received message with content = average-agent-3 20 average-agent-0 78 average-agent-1 59 
average-agent-1 agent received message with content = average-agent-4 5 average-agent-2 50 average-agent-3 20 average-agent-0 78 average-agent-1 59 
average-agent-1 agent sent inform message with content = average-agent-4 5 average-agent-2 50 average-agent-3 20 average-agent-0 78 average-agent-1 59 
average-agent-1 agent calculated average = 42.4
```
