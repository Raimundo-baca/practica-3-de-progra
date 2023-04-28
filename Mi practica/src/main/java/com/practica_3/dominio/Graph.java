package com.practica_3.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Graph<V>{


//Lista de adyacencia.
private Map<V, Set<V>> adjacencyList = new HashMap<>();


public boolean addVertex(V v){
    if(!containsVertex(v)){
        adjacencyList.put(v, new HashSet<V>());
        return true;
}
    else
        return false; 
    }

public boolean addEdge(V v1, V v2){

        if(!containsVertex(v1))
            addVertex(v1);
        if(!containsVertex(v2))
            addVertex(v2);
        if(adjacencyList.get(v1).contains(v2))
            return false;
        else{
            adjacencyList.get(v1).add(v2);
            return true;
        }
    }

    public Set<V> obtainAdjacents(V v) throws Exception{
        if (!containsVertex(v))
        throw new Exception("El vértice no existe.");
        else 
        return adjacencyList.get(v);
    }

    public boolean containsVertex(V v){
    return adjacencyList.containsKey(v);
    }

    public String toString(){

        String s = adjacencyList.toString();
        return s;

        }

        public List<V> onePath(V v1, V v2) throws Exception{
       
            Map<V, V> traza = new HashMap<>();

            Stack<V> abierta = new Stack<>();

            abierta.push(v1);
            traza.put(v1, null);

            boolean encuentrado = false;
            
            while (!abierta.isEmpty() && !encuentrado ) {
                V v = abierta.pop();
                encuentrado  = v.equals(v2);
                if (!encuentrado) {
                    for (V s : obtainAdjacents(v)) {
                        abierta.push(s);
                        traza.put(s, v);
                    }
                }
            }
            if (encuentrado ) {
                List<V> camino = new ArrayList<>();
                V caminoActual = v2;
                while (caminoActual != null) {
                    camino.add(caminoActual);
                    caminoActual = traza.get(caminoActual);
                }
                Collections.reverse(camino);
                return camino;
            } else {
                return null;
            }
        }

}




