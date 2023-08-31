package com.apress.prospring5.ch4.sec7;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * 4.7 자바빈 PropertyEditor
 * 4.7.1 스프링이 제공하는 PropertyEditor 사용하기
 *
 * 스프링이 내장 PropertyEditor를 사용해 문자열로 표현된 프로퍼티 값을
 * 올바른 타입으로 변환했음을 알 수 있다.(p.256)
 *
 * InputStream은 에러가 나서 주석처리했다ㅠ
 *
 */
public class PropertyEditorBean {
    private byte[] bytes;
    private Character character;
    private Class cls;
    private Boolean trueOrFalse;
    private List<String> stringList;
    private Date date;
    private Float floatValue;
    private File file;
    private InputStream stream;
    private Locale locale;
    private Pattern pattern;
    private Properties properties;
    private String trimString;
    private URL url;

    public void setBytes(byte[] bytes) {
        System.out.println("==========setBytes : " + bytes);
        this.bytes = bytes;
    }

    public void setCharacter(Character character) {
        System.out.println("==========setCharacter : " + character);
        this.character = character;
    }

    public void setCls(Class cls) {
        System.out.println("==========setClass : " + cls);
        this.cls = cls;
    }

    public void setTrueOrFalse(Boolean trueOrFalse) {
        System.out.println("==========setBoolean : " + trueOrFalse);
        this.trueOrFalse = trueOrFalse;
    }

    public void setStringList(List<String> stringList) {
        System.out.println("==========setStringList : " + stringList);
        this.stringList = stringList;
    }

    public void setDate(Date date) {
        System.out.println("==========setDate : " + date);
        this.date = date;
    }

    public void setFloatValue(Float floatValue) {
        System.out.println("==========setFloatValue : " + file);
        this.floatValue = floatValue;
    }

    public void setFile(File file) {
        System.out.println("==========setFile : " + file);
        this.file = file;
    }

    public void setStream(InputStream stream) {
        System.out.println("==========setSInputStream : " + stream);
        this.stream = stream;
    }

    public void setLocale(Locale locale) {
        System.out.println("==========setLocale : " + locale);
        this.locale = locale;
    }

    public void setPattern(Pattern pattern) {
        System.out.println("==========setPattern : " + pattern);
        this.pattern = pattern;
    }

    public void setProperties(Properties properties) {
        System.out.println("==========setProperties : " + properties);
        this.properties = properties;
    }

    public void setTrimString(String trimString) {
        System.out.println("==========setString : " + trimString);
        this.trimString = trimString;
    }

    public void setUrl(URL url) {
        System.out.println("==========setUrl : " + url);
        this.url = url;
    }

    public static class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar{
        @Override
        public void registerCustomEditors(PropertyEditorRegistry registry) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            registry.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
            registry.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        }
    }

    public static void main(String[] args) throws Exception{
        File file = File.createTempFile("test","txt");
        file.deleteOnExit();

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch4/sec7/app-context-01.xml");
        ctx.refresh();

        PropertyEditorBean bean = (PropertyEditorBean) ctx.getBean("builtInSample");
        ctx.close();

    }
}
