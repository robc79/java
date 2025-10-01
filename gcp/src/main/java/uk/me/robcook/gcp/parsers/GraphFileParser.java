package uk.me.robcook.gcp.parsers;

import java.io.PrintStream;

import uk.me.robcook.gcp.domain.Graph;

public interface GraphFileParser
{
    Graph parse(final String filename, final PrintStream err);    
}
