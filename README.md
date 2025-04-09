# code-assessment
The step to run the code
1. Compile java file
   
   `javac FileSystem.java`
3. Run the class file
   
   `java FileSystem`
5. Then enter the commands, such as
   ```
   CREATE fruits
   CREATE vegetables
   CREATE grains
   CREATE fruits/apples
   CREATE fruits/apples/fuji
   LIST
    fruits
      apples
        fuji
    grains
    vegetables
    CREATE grains/squash
    MOVE grains/squash vegetables
    CREATE foods
    MOVE grains foods
    MOVE fruits foods
    MOVE vegetables foods
    LIST
    foods
      fruits
        apples
          fuji
      grains
      vegetables
        squash
    DELETE fruits/apples
    Cannot delete fruits/apples - fruits does not exist
    DELETE foods/fruits/apples
    LIST
    foods
      fruits
      grains
      vegetables
        squash
```
    
