ДЗ-2 (Тестирование JUnit)
------------------------------------
Приложение состоит из трех классов:
1. Product.class - товар(продукт), поступающий на склад и продаваемый магазином,
   характеризуется названием товара, стандартной ценой, и, опционально, акционной
   ценной при покупке акционного количества товара
2. WareHouse.class - склад магазина (singleton), хранящий продукты;
3. Store.class - магазин, через который пользолватель может: 
   - добавлять в корзину товар, имеющийся на складе; 
   - удалять ранее добавленный товар из корзины; 
   - показывает состав корзины;
   - очищает корзину;
   - считает итоговую стоимость корзины.