package org.bah.interpreter;


import java.util.HashMap;
import java.util.Map;

public class Environment {
    private final Map<String, Double> variables = new HashMap<>();

    public void assign(String name, double value) {
        variables.put(name, value);
    }

    public double get(String name) {
        if (variables.containsKey(name)) {
            return variables.get(name);
        }
        throw new RuntimeException("Variable '" + name + "' is not defined.");
    }
}
