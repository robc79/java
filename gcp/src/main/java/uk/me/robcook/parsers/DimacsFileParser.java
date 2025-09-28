package uk.me.robcook.parsers;

import uk.me.robcook.domain.Graph;

public class DimacsFileParser implements GraphFileParser
{
    @Override
    public Graph parse(String filename) {
        return new Graph();
    }
}
