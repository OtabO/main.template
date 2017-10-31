package util.template;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

/**
 * Created by zhangsx on 2017/6/29.
 */
public class MyArrayList<E> implements List<E> {

    private Object[] elements;

    private int initSize;

    private int extSize;

    private int capacity;

    private int size=0;

    public MyArrayList(){
        this(10,10);
    }

    public MyArrayList(int initSize){
        this(initSize,10);
    }

    public MyArrayList(int initSize,int extSize){
        if(initSize<0||extSize<0){
            throw new RuntimeException();
        }
        this.initSize=initSize;
        this.extSize=extSize;

        elements=new Object[initSize];
        capacity=elements.length;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size<=0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o)>=0;
    }

    private class InternalIterator<E> implements Iterator<E>{

        private int cursor=-1;

        private int expectedSize=size;
        @Override
        public boolean hasNext() {
            return cursor+1<size();
        }

        @Override
        public E next() {
            if(expectedSize!=size){
                throw new RuntimeException("不能在iterator读取过程中更新数组");
            }
            cursor++;
            return (E) elements[cursor];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new InternalIterator();
    }

    @Override
    public Object[] toArray() {
        return elements;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        ext(1);
        elements[size()]=e;
        size++;
        return true;
    }

    private void ext(int need){
        if(size()+need>capacity){
            Object[] oldElements=elements;
            Object[] eleTmp=new Object[capacity+extSize+need];
            for(int i=0;i<oldElements.length;i++){
                eleTmp[i]=oldElements[i];
            }
            elements=eleTmp;
            capacity=capacity+extSize+need;
        }
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        if(o==null){
            for(int i=0;i<size();i++){
                if(elements[i]==null){
                    return i;
                }
            }
        }else{
            for(int i=0;i<size();i++){
                if(o.equals(elements[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
        String s="1";
        String s1="2";
        URL[] urls=new URL[]{new URL("file://Users/zhangsx/projects/test.spring/target/classes")};
        URLClassLoader urlClassLoader=new URLClassLoader(urls,ClassLoader.getSystemClassLoader());
        URLClassLoader urlClassLoader2=new URLClassLoader(urls,ClassLoader.getSystemClassLoader());
        Class clazz1=urlClassLoader.loadClass("test.spring.B2");
        Class clazz2=urlClassLoader2.loadClass("test.spring.B2");

        System.out.println(clazz1==clazz2);
    }
}
