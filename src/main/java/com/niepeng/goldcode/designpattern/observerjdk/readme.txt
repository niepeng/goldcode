使用jdk自带的观察者模式的缺点：

1，Observable是一个类，而不是一个接口，导致Observable类的扩展性不高，不如自己实现的观察者模式灵活

2，Observable将某些方法保护了起来(setChanged()和clearChanged()为protected)，这意味着除非继承自Observable，否则将有关键的方法不能调用。
导致无法通过组合的方式使其它类获得Observable类的功能

来源：
http://blog.csdn.net/a19881029/article/details/8975962
