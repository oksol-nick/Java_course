Feature: Поиск товара
  Background:
    Given Пользователь находится на главной странице системы

  @myTag
  Scenario: Получение списка подсказок при вводе названия товара

    When Пользователь вводит название товара в поисковую строку <"spri">
    Then Под поисковой строкой появляется список с возможными вариантами товара:
      |Spring быстро|
      |Spring 5 для профессионалов|

  @myTag
  Scenario: Выбор подсказки из списка
    And В поисковой строке частично введено название товара <"spri">
    And Под поисковой строкой сформирован список с возможными вариантами товара:
      |Spring быстро|
      |Spring 5 для профессионалов|
    When Пользователь выбирает щелком левой клавиши мыши первый элемент списка подсказок <"Spring быстро">
    Then Частично введенное пользователем название товара изменяется на название товара из первого элемента списка подсказок <"Spring быстро">

  @myTag
  Scenario: Получение результатов поиска товара
    And В поисковой строке введено название товара <"Spring быстро">
    When Пользователь нажимает кнопку НАЙТИ <"CLICK">
    Then Пользователь перенаправляется на страницу с результатами поиска:
    |name         |type             |price  |
    |Spring быстро|Бумажная книга   |3500   |
    |Spring быстро|Электронная книга|1350   |

  @myTag
  Scenario Outline: Товар не найден
    And В поисковой строке введено некорректное название товара "<wrong_good_name>"

    When Пользователь нажимает кнопку НАЙТИ_2 <"CLICK">
    Examples:
      |wrong_good_name   |
      |Spring easy       |
      |Spring for dummies|

