package com.company;

import com.domain.Person;

import java.util.*;

public class MainDataStructures {

    private static void workWithSets() {
        Set hashSet = new HashSet(); //this can have loading factor and initial capacity as params
        Set linkedHashSet = new LinkedHashSet();
        Set treeSet = new TreeSet();

        Person firstPerson = new Person("1", "Ana");
        Person secondPerson = new Person("2", "John");
        Person thirdPerson = new Person("3", "Kate");
        Person forthPerson = new Person("1", "Paul");

        hashSet.add(secondPerson);
        hashSet.add(firstPerson);
        hashSet.add(thirdPerson);
        hashSet.add(forthPerson);
        hashSet.remove(forthPerson);

        linkedHashSet.add(secondPerson);
        linkedHashSet.add(firstPerson);
        linkedHashSet.add(thirdPerson);
        linkedHashSet.add(forthPerson);
        linkedHashSet.remove(forthPerson);

        treeSet.add(secondPerson);
        treeSet.add(thirdPerson);
        treeSet.add(firstPerson);
        treeSet.remove(secondPerson);

        System.out.println("Hash set: " + hashSet.toString());
        System.out.println("Linked hash set: " + linkedHashSet.toString());
        System.out.println("Tree set: " + treeSet.toString());

    }

    private static void workWithQueue() {
        Person firstPerson = new Person("1", "Ana");
        Person secondPerson = new Person("2", "John");
        Person thirdPerson = new Person("3", "Kate");
        Person forthPerson = new Person("1", "Paul");
        Queue priorityQueue = new PriorityQueue(new Comparator() {
            @Override
            public int compare(Object o, Object t1) {
                return ((Person) o).getName().compareTo(((Person) t1).getName());
            }
        });

        priorityQueue.add(secondPerson);
        priorityQueue.add(thirdPerson);
        priorityQueue.add(firstPerson);

        System.out.println("Priority queue: " + priorityQueue.toString());
    }


    private static void workWithMaps(){
        Person firstPerson = new Person("1", "Ana");
        Person secondPerson = new Person("2", "John");
        Person thirdPerson = new Person("3", "Kate");
        Person forthPerson = new Person("1", "Paul");

        Map<String, Person> hashtable=new Hashtable<>();
        hashtable.put(firstPerson.getId(),firstPerson);
        hashtable.put(secondPerson.getId(),secondPerson);
        hashtable.put(thirdPerson.getId(),thirdPerson);
        hashtable.put(forthPerson.getId(),forthPerson);

        System.out.println(hashtable.toString());
    }

    public static void main(String[] args) {
        workWithSets();
        workWithQueue();
        workWithMaps();
    }
}
