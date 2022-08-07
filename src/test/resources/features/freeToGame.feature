@freeToGame
  Feature: Consulta de juegos por plataforma y categoria
  @t1
  Scenario: Consulta exitosa de juegos seleccionados por plataforma y categoria
    Given que la aplicacion este operativa
    When obtiene los parametros platform "pc" y category "shooter"
    Then valido que la respuesta sea 200
    And imprima el id y el title de todos los registros de la consulta

  @t2
  Scenario: Consulta fallida de juegos seleccionados por plataforma y categoria
    Given que la aplicacion este operativa
    When obtiene los parametros platform "nada" y category "nada"
    Then valido que la respuesta sea 404