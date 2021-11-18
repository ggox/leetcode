package com.ggox.leetcode.designpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 访问者模式
 *
 * @author: ggox
 * @date: 2021/11/18 22:33
 * @description:
 */
public class VisitorPattern {

    interface Element {
        void accept(Visitor visitor);
    }

    static class ElementA implements Element {
        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    static class ElementB implements Element {
        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    interface Visitor {
        void visit(ElementA elementA);
        void visit(ElementB elementB);
    }

    static class NameVisitor implements Visitor{
        @Override
        public void visit(ElementA element) {
            System.out.println("A");
        }

        @Override
        public void visit(ElementB elementB) {
            System.out.println("B");
        }
    }

    public static void main(String[] args) {
        final ElementA elementA = new ElementA();
        final ElementB elementB = new ElementB();
        List<Element> elements = new ArrayList<>();
        elements.add(elementA);
        elements.add(elementB);
        Visitor visitor = new NameVisitor();
        for (Element element : elements) {
            element.accept(visitor);
        }
    }
}
