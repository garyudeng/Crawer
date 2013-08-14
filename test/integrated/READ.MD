集成调用说明：

1、集成在抽取之后进行。
2、调用集成接口，必须先实例化spring IOC容器。
ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/beans.xml");

3、实例化相关的集成类（如：当当网-->DDIntegrated.java）
然后通过
Integrated in = (DDIntegrated)ctx.getBean("bean name");
获取您要操作的对象,(注意转型)

4、调用集成方法：
in.integrated(ddbook);
//传入的参数ddbook，是由抽取部分完成的！



	




