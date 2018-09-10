#!/usr/bin/env bash
ASPECTJ_WEAVER=/d/maven_new_repository/org/aspectj/aspectjweaver/1.8.13/aspectjweaver-1.8.13.jar
ASPECTJ_RT=/d/maven_new_repository/org/aspectj/aspectjrt/1.8.13/aspectjrt-1.8.13.jar
ASPECTJ_TOOLS=/d/maven_new_repository/org/aspectj/aspectjtools/1.8.13/aspectjtools-1.8.13.jar
java -jar $ASPECTJ_TOOLS -cp $ASPECTJ_RT -source 1.8 -sourceroots src/main/java/ -d target/classes
