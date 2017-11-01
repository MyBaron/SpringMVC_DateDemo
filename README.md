# SpringMVC_DateDemo
> 因为SpringMVC 不会自动将String类型转换成时间类型，因此需要自己写工具类来实现转换

## 本篇主要分3点。
		1. 测试SpringMVC 对时间类型的转换问题 
		2. 如何解决。
		3. 简单讲解一下原理

> 此例子转换两种数据类型
> - Date类型
> - Timestamp 类型

## Controller 类
``` java
@Controller
@RequestMapping("/Test")
public class testController {


    @RequestMapping("/form")
    @ResponseBody
    private String form(TestEntity testEntity){
        System.out.println("测试成功");
        System.out.println(testEntity);
        return "ok";
    }
}
```

## Entity实例类
``` java
    private String name;
    private String Data;
    private Date date;
    private Timestamp timestamp;
```

## 测试页面代码
```
 <label >姓名
    <input type="text" name="name"/>
    </label>
    <label >Date
    <input type="text" name="Data"/>
    </label>
    <label >date
    <input type="text" name="date"/>
    </label>
    <label >Timestamp
    <input type="text" name="timestamp"/>
    </label>
```

## 开始测试
1. 首先测试String 类型的转换
也就是最常用的 转换成的String类型

![1.jpg](http://upload-images.jianshu.io/upload_images/6212571-4e122f69750b603a.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

后端获取到的数据
``TestEntity{name='蓝蓝', Data='2017-8-16', date=null, timestamp=null}``

2. 测试转换Date类型的

![2.jpg](http://upload-images.jianshu.io/upload_images/6212571-9712010fb6eec29e.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

第三个数据是转换成Date类型的。提交的时候出现

![2.1.jpg](http://upload-images.jianshu.io/upload_images/6212571-709bdff2e63c39bb.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

> 那是因为SpringMVC 并不能直接把String转成Date类型

解决办法： 
	1. 写一个工具类 实现Coverter接口
```

public class DateConverter implements Converter<String,Date> {

    public Date convert(String source) {
        String pattern = source.length()==10 ? "yyyy-MM-dd" : "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

```

	2. 在SpringMvc 的配置文件中注册 Converter

```
 <mvc:annotation-driven conversion-service="customConversionService">
    </mvc:annotation-driven>
    <bean id="customConversionService" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
        <property name="converters">
            <set>
                <bean class="com.Util.DateConverter"/>
                <bean class="com.Util.TimeSteamp"/>
            </set>
        </property>
    </bean>
```

效果图：

![3.jpg](http://upload-images.jianshu.io/upload_images/6212571-7e2a386f5aa9a1e0.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


后端获取到的数据
``TestEntity{name='123', Data='2017-11-1', date=2017-11-01, timestamp=null}``

3. 转换成Timesteam类型

同理转换类：
```
public class TimeSteamp implements Converter<String,Timestamp> {

    public Timestamp convert(String source) {
        /**
         * 注意Timestamp 只能是yyyy-MM-dd HH:mm:ss 这种格式
         */
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return new Timestamp(format.parse(source).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
```

测试图：

![4.jpg](http://upload-images.jianshu.io/upload_images/6212571-caa3974e1ef3ab5d.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

``TestEntity{name='蓝蓝', Data='2017-11-1', date=2017-11-01, timestamp=2017-11-01 12:23:12.0}``


总结： 以上就是SpringMVC转换时间类型的方法


接下来简单的讲解一下我对于这个``org.springframework.format.support.FormattingConversionServiceFactoryBean`` 类的一些理解

- 首先它是一个工厂类，配置有转换器和格式化程序，用于常见类型，如数字和数据时间。
- 可以通过两个方法来声明注册。转换器：``setConverters（Set）``和格式化器：``setFormatters（Set）``
- 刚刚我在SpringMVC配置声明这个类，就注册了这个``setConverters（Set）`` 这个方法
- 再讲解一下我写的转换类，也是实现了Converter接口的。

如有错误，欢迎指出。