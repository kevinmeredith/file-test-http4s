# How to Run Example

1. Run server via `sbt run`
2. Open html/file-test.html
3. Select a file
4. Hit Submit
5. Verify that you see a message showing something like:

```
sbt:file-test-http4s> run
[info] Packaging /Users/kevinmeredith/Workspace/file-test-http4s/target/scala-2.12/file-test-http4s_2.12-0.1.0-SNAPSHOT.jar ...
[info] Done packaging.
[info] Running (fork) net.Main 
[error] SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
[error] SLF4J: Defaulting to no-operation (NOP) logger implementation
[error] SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
[info] fileName: a.txt with text: asdfasdfadf
```

# Reference/Credit

https://stackoverflow.com/questions/8659808/how-does-http-file-upload-work helped me to understand, at a high level, the client + HTTP components
of uploading a file.