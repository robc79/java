package uk.me.robcook.gcp.parsers;

import uk.me.robcook.gcp.domain.Graph;

public interface GraphFileParser
{
    Graph parse(String filename);    
}
