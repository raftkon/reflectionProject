# Reflection Project

A simple project about the java Reflection API.
## Requirements
The script requires the invoker to provide:
* an input text file with the types you want to search for
* an output text file to write the results and
* the top-N results.
#### The invoker is responsible for providing the right classpath. 

## Notes
After compiling all .java files, run the command below: 
* java -cp <\classpath> Main <\input file> <\output file> <\top-N>

In case the script throws error about not finding the Main class, try to rearrange the classpaths and the error will be resolved.