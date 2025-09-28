package uk.me.robcook.parsers;

import uk.me.robcook.domain.Graph;

public class DimacsFileParser implements GraphFileParser
{
    @Override
    public Graph parse(String filename) {
        // TODO: Parse filename as a DIAMCS text file for the graph colouring problem.
        
        return new Graph();
    }
}
