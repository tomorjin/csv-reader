# csv-reader
In order to use initialize Transformer, need to provide configuration file's absolute path. Configuration file is documented in itself.

```
Transformer transformer = Transformer.getInstance("D:\csv-reader.properties");
```

Then need to proved csv file path and it begins to load
```
transformer.load("D://order.csv");
```

when data is not allowed or not valid the module will write console log ``` #INVALID_ROW ``` key word. 
It's for grabbing all invalid rows after transformation. 

### In the future or if it's in real project
Use thread or parallel programming to proceed huge amount of data.
Use Transformer as a batch job.