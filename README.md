# UdemyWeatherApp
Learn Android Development by https://www.udemy.com/android2master/learn/v4/overview

# Diff of Volley
I fixed bellow
```
# volley/bintray.gradle
- publish = project.has("release")
+ publish = project.hasProperty("release")
```
