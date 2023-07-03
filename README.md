# Incremental-class2relational
A BXtendDSL solution for the TTC2023 case: Incremental MTL vs. GPLs: Class into Relational Database Schema Transformation Case

## The case revealed a bug in our code generation engine

To make the solution run, please do the following, after Code has been generated from the BXtendDSL specification:

Goto "src-gen" folder and to the "rules" package. Comment the following line in "Class2Table.xtend"

```clz.getAttr().forEach[corr.assertRuleId("SingleAttribute2Column", "SingleClassAttribute2Column", "MultiAttribute2Table")]```

Change line ```val _col = colFrom(_attSinCol, _attSinCol_2, _attMulTbl)``` 

to 

```val _col = colFrom(_attSinCol, _attSinCol_2, _attMulTbl, tbl)```

and finally, change line

```def protected abstract Type4col colFrom(List<relational_.Column> attSinCol, List<relational_.Column> attSinCol_2, List<relational_.Table> attMulTbl);``` 

to


```def protected abstract Type4col colFrom(List<relational_.Column> attSinCol, List<relational_.Column> attSinCol_2, List<relational_.Table> attMulTbl, relational_.Table parent);```
