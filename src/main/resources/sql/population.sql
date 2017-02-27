
select p.code ,p.name, ifnull(round(base.cnt),0) population
from (
select AA.name ,count(AA.name) cnt
from (SELECT SUBSTRING_INDEX(SUBSTRING_INDEX(a.address, ' ', -2),' ',1) name , a.* from report a ) AA
group by AA.name) base
right outer join population p
on base.name  = p.name

