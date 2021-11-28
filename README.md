# Pedometer_manager_LearnUp

## Менеджер шагомера
Реализован класс PedometerManager, представляющий собой программу, позволяющую добавлять, изменять и получать количество шагов за определенные дни (нумерация дней ведётся с 1).

### PedometerManager
Имплементирует интерфейс Comparable   
**getSteps(int day)** - возвращает количество шагов за день day   
**addSteps(int steps)** - добавляет новый день с количеством шагов steps   
**getMaxDay()** - возвращает номер дня с максимальным количеством шагов   
**add(int day, int steps)** - увеличивает количество шагов на steps за день day и возвращает, сколько шагов ещё нужно было пройти в день day, чтобы количество шагов стало максимальным за все дни   
**getDaysList()** - возвращает список шагов за все дни   
**compareTo(PedometerManager o)** - сравнивает себя и объект "o" по сумме шагов за все дни   
### StepBattle
Класс, принимающий в конструкторе 2 менеджера шагомеров    
**addSteps(int player, int day, int steps)** - добавляет шаги в первый или второй шагомер (номер определяется первым параметром: 1 или 2)   
**int winner()** - возвращает номер выигравшего игрока (игрок, у кого в сумме было больше шагов)
### PedometerManagerDaysComparator
Имплементирует интерфейс Comparator. В конструкторе принимает минимальное количество шагов.   
**compare(PedometerManager o1, PedometerManager o2)** - сравает "o1" и "o2" по количеству дней, в которые пройдено больше шагов, чем значение минимума (определённое в конструкторе)   
## Дополнительно
Программа собирается с помощью фреймворка Apache Maven.  
При каждом новом коммите происходит запуск тестов благодаря настройке Maven CI на основе Github Actions.   
В тестах используется фреймворк Mockito.