package uk.me.robcook.rosalind.args;

import java.io.File;

public record TranscribeDnaArgs(File dnaFile, File rnaFile) implements Args
{
}
