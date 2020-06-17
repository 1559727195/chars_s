package com.massky.chars_s.create;


public class Person  {
    private int ID;
    private int age;
    private String name;
    private int hight;
    private int weight;
    private Callback callback;

    interface Callback {
        void callback();
    }

    private Person(Builder builder) {
        this.ID = builder.ID;
        this.age = builder.age;
        this.name = builder.name;
        this.hight = builder.hight;
        this.weight = builder.weight;
        this.callback = builder.callback;
    }

    public static class Builder {
        private int ID;
        private int age;
        private String name;
        private int hight;
        private int weight;
        private Callback callback;

        public Builder setID(int ID) {
            this.ID = ID;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setHight(int hight) {
            this.hight = hight;
            return this;
        }

        public Builder setWeight(int weight) {
            this.weight = weight;
            return this;
        }

        public Builder setCallback(Callback callback) {
            this.callback = callback;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}