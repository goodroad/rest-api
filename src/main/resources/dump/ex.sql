

update express_milestone
set keyword =  concat(name,'ic')
where road_type =1

insert into report(writer_type , write_date , group1 , group2 , lat, lng)
select 3 writer_type , write_date , group1 , group2 , lat, lng
from ex
where line = '중부선'e

