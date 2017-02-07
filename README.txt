HADOOP STUDY

MapReduce

MapReduce개념
MapReduce는 HDFS에 저장된 파일을 분산 배치 분석을 할 수 있도록 도와주는 분산 프레임워크.
개발자는 프로그래밍모델에 맞게 애플리케이션을 구현만 하면 된다.
나머지 데이터 전송, 분산 처리, 내고장성등의 복잡한 처리는 MapReduce프레임워크가 처리해준다.
MapReduce는 Map과 Reduce 두 단계로 데이터를 처리한다.

1. Map
Map 단계는 입력받은 데이터(ex - HDFS의 파일)를  Key, Value의 형태로 데이터를 분류하는 단계다.
기본적인 방식을 표현한다면 아래와 같다.
(K1, V1) -> list(K2, V2)

Ex.
입력 파일
read a book
write a book
두줄로 이루어진 txt파일.

첫번째라인, read a book -> {"read" : 1, "a" : 1, "book" : 1}
두번째라인 , write a book -> {"write" : 1, "a" : 1, "book" : 1}

2.Reduce
Map 단계에 나온 출력을 집계하는 단계입니다. 출력은 Key, Value의 형태입니다.
기본적인 방식을 표현한다면 아래와 같습니다.
(K2, list(V2)) -> (K3, list(V3))

위의 Map예제에서 이어서 보겠습니다.
Ex.
{"read" : 1, "a" : 1, "book" : 1}, {"write" : 1, "a" : 1, "book" : 1} -> {"a" : 2, "book" : 2, "read" : 1, "write" : 1}

