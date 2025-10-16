package uk.me.robcook.gcp.domain;

import java.io.PrintStream;
import java.util.List;

public class Colouring
{
    private final List<List<Integer>> colourAssignments;

    public Colouring(List<List<Integer>> colourAssignments)
    {
        this.colourAssignments = colourAssignments;
    }
    
    public int getAssignemntCount()
    {
        return colourAssignments.size();
    }
    
    public void print(PrintStream out)
    {
        out.println("Colouring: ");
        out.println();
        
        for(var i=0; i<colourAssignments.size(); i++)
        {
            out.print(String.format("%d -> ", i));

            for(var vertex : colourAssignments.get(i))
            {
                out.print(String.format("%d ", vertex));
            }

            out.println();
        }
    }
}
