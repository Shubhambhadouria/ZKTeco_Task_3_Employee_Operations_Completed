# 🎯 ZKTeco_Task_3_Spring_Boot_Employee_Operations_Completed

1. Create Spring boot application for Employee operations. 

2. Employee will be having the properties such as id, first name, last name, gender, email, phone, password, date of birth and profile photo,
created date and updated date, department & designation, where Id, email and phone are unique for each Employee. 

3. Validations should be there for email(correct email format eg: Employee@xxx.in, Employee@xxx.com, Employee@xxx.net, Employee@xxx.orgs and etc.), phone(correct phone number format. eg: +91-7799551277 i.e +country code-phone number).A password must have at least 8 characters, consists of only letters and digits, must contain at least two digits & atleast one uppercase character.

4. Id, first name, email, phone & password are mandatory fields and the rest are optional while Employee creation.

5. The max field legnths can be as follows </br>
  id: 36</br>
  first name: 50    </br>
  last name: 50     </br>
  gender : 1    </br>
  password: min 8 and max 20   </br>
6.Application should have the following APIs for Employee operations      </br>
create Employee,        </br>
create batch Employees,       </br>
update Employee info by id,       </br>
fetch Employee details by id|email|phone         </br>
fetch all Employees  </br>
fetch all Employees created b/w to dates(from date & to date) </br>
delete the Employee by id.  </br>
7. There should be an option to create different departments and designations and also application has to support all the  below operations for department(with fields id, name, code, created date & updated date) & designation(with fields id, name, code, created date & updated date) and here id(36), code(30) and name(50) are mandatory fields & unique].Also make sure length of the fields can not exceeds than the given.         </br>
create department, </br>
create batch departments, </br>
update department info by id,  </br>
fetch department details by id   </br>
fetch all departments   </br>
delete the department by id.     </br>
create designation,  </br>
create batch designations,  </br>
update designation info by id,  </br>
fetch designation details by id    </br>
fetch all designation   </br>
delete the designation by id. </br>
8. There has to be an option i.e employee can be assigned to designation & department and when employee details are being fetched, associated department and designation info also to be fetched.      </br>
9.Custom & Global exceptions will be handled.   </br>
10.Loggers should be implemented. </br>
11.Application should have actuator end points to verify application's health, metrics & info.  </br>
12. Application should support different locales for error messages(i18n)(for this application just go with English, Chinese & Spanish).
