# GCP

Simple command line utility for solving the graph colouring problem using the
saturation degree heuristic. Works on `.col` files in DIMACS format.

## Running

Package the project using standard Maven commands. Run the resulting `.jar` file
with a single command line argument - tha name of the graph file to parse. The
output will be a list of grouped node ids and a count of the colours used.
