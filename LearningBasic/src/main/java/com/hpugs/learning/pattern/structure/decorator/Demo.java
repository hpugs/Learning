package com.hpugs.learning.pattern.structure.decorator;

import java.io.IOException;

/**
 * 装饰器模式
 *
 * @author gaoshang
 * date: 2020/12/1 下午3:53
 */
public class Demo {

    public static void main(String[] args) {
        try {
            DataSource dataSource = new FileDataSource("DecoratorDemo.txt");
            dataSource.write("FileDataSource write");
            String read = dataSource.read();
            System.out.println(read);

            System.out.println("--------------------------");

            DataSourceDecorator dataSourceDecorator = new EncryptionDecorator(new UpperCaseDecorator(dataSource));
            dataSourceDecorator.write("EncryptionDecorator -> UpperCaseDecorator -> FileDataSource write");
            read = dataSourceDecorator.read();
            System.out.println(read);

            System.out.println("--------------------------");

            dataSourceDecorator = new UpperCaseDecorator(new EncryptionDecorator(dataSource));
            dataSourceDecorator.write("EncryptionDecorator -> UpperCaseDecorator -> FileDataSource write");
            read = dataSourceDecorator.read();
            System.out.println(read);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
