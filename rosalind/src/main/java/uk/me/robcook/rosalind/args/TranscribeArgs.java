package uk.me.robcook.rosalind.args;

import java.io.File;

public record TranscribeArgs(File dnaFile, File rnaFile) implements Args
{
}
