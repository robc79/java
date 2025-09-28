package uk.me.robcook.parsers;

import uk.me.robcook.domain.Graph;

public interface GraphFileParser
{
    Graph parse(String filename);    
}
