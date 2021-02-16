# 에이치디정션 백엔드 개발자 채용 온라인 과제

## 기술 스택
이 프로젝트에 사용된 기술은 다음과 같습니다.

- Language: Java
- Project: Gradle Project

## 프로젝트 설명 
1) 환자 등록 : `POST /patient`
- Request :  Body (application/json )
  
  | Physical Name | Logical Name  | default      | required     |
  | ------------- | ------------- |------------- |------------- |
  | name          | 환자명         |              | `true`       |
  | birthday      | 생년월일        |              | `true`       |
  | genderCode    | 성별코드        |              | `true`       |
  | cellphone     | 휴대전화번호     |              | `true`       |
  | hospitalId    | 병원 Id        |              | `true`       |

- Response
  
  | Physical Name | Logical Name  |
  | ------------- | ------------- |
  | id            | 환자 Id         |

2) 환자 수정 : `PUT /patient`
- Request :  Body (application/json )

  | Physical Name | Logical Name  | default      | required     |
  | ------------- | ------------- |------------- |------------- |
  | name          | 환자명         |              | `true`       |
  | birthday      | 생년월일        |              | `true`       |
  | genderCode    | 성별코드        |              | `true`       |
  | cellphone     | 휴대전화번호     |              | `true`       |
  | hospitalId    | 병원 Id        |              | `true`       |

- Response

  | Physical Name | Logical Name  |
  | ------------- | ------------- |
  | id            | 환자 Id         |

3) 환자 삭제 : `Delete /patient/{id}`
- Request :  Path Parameter
  
  | Physical Name | Logical Name  | default      | required     |
  | ------------- | ------------- |------------- |------------- |
  | id            | 환자 Id        |              | `true`       |

- Response

  | Physical Name | Logical Name  |
  | ------------- | ------------- |
  | id            | 환자 Id         |

4) 환자 삭제 : `Delete /patient/{id}`
- Request :  Path Parameter

  | Physical Name | Logical Name  | default      | required     |
  | ------------- | ------------- |------------- |------------- |
  | id            | 환자 Id        |              | `true`       |

- Response

  | Physical Name | Logical Name  |
  | ------------- | ------------- |
  | id            | 환자 Id         |

4) 환자 조회 : `Get /patient/{id}`
- Request :  Path Parameter

  | Physical Name | Logical Name  | default      | required     |
  | ------------- | ------------- |------------- |------------- |
  | id            | 환자 Id        |              | `true`       |

- Response

  | Physical Name | Logical Name  |
  | ------------- | ------------- |
  | id            | 환자 Id         |
  | name          | 환자명         |
  | birthday      | 생년월일        |
  | genderCode    | 성별코드        |
  | cellphone     | 휴대전화번호     |
  | hospital      | 병원           |
  | - id          | 병원 Id        |
  | - name        | 병원명         |
  | - tel         | 요양기관번호     |
  | - directorName| 병원장명       |

5) 환자 목록 조회 : `Get /patient`
- Request :  Path Parameter

  | Physical Name | Logical Name  | default      | required     |
  | ------------- | ------------- |------------- |------------- |
  | id            | 환자 Id        |              | `true`       |

- Request :  Get Parameter

  | Physical Name | Logical Name  | default      | required     |
  | ------------- | ------------- |------------- |------------- |
  | pageNo        | 환자 Id        | 1            | `false`      |
  | pageSize      | 환자 Id        | 10           | `false`      |
  | seq           | 환자번호        |              | `false`      |
  | name          | 환자명          |             | `false`       |
  | birthday      | 생년월일        |              | `false`      |

- Response

  | Physical Name | Logical Name  |
    | ------------- | ------------- |
  | id            | 환자 Id         |
  | name          | 환자명         |
  | birthday      | 생년월일        |
  | genderCode    | 성별코드        |
  | cellphone     | 휴대전화번호     |
  | hospital      | 병원           |
  | - id          | 병원 Id        |
  | - name        | 병원명         |
  | - tel         | 요양기관번호     |
  | - directorName| 병원장명        |
