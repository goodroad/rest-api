INSERT INTO`goodroad`.`express_milestone`
(
`num`,
`name`,
`eng_name`,
`distance`,
`miles`
)
VALUES
('0','부산기점','','0','0.00')
,('1','삼락','Samnak','0.20','0.20')
,('2','대저분기점','DaejeoJC','2.94','3.14')
,('3','대동분기점','DaedongJC','6.98','10.12')
,('4','상동','Sangdong','6.64','16.76')
,('5','삼랑진','Samnangjin','15.29','32.05')
,('6','남밀양','S.Miryang','7.56','39.61')
,('7','밀양','Miryang','6.03','45.64')
,('8','청도','Cheongdo','18.03','63.67')
,('9','수성','Suseong','22.30','85.9')
,('10','동대구','E.Daegu','3.60','89.57')
,('11','동대구분기점','E.DaeguJC','2.60','92.17')
,('12','도동분기점','DodongJC','','')
,('13','북대구','N.Daegu','','')
,('14','금호분기점','GeumhoJC','16.33','108.50')
,('15','칠곡','Chilgok','4.78','113.28')
,('16','다부','Dabu','13.90','127.18')
,('17','가산','Gasan','6.28','133.46')
,('18','군위','Gunwi','12.11','145.57')
,('19','의성','Uiseong','11.00','156.57')
,('20','안동분기점','AndongJC','','')
,('21','남안동','S.Andong','22.73','179.30')
,('22','서안동','W.Andong','15.00','194.30')
,('23','예천','Yecheon','13.30','207.60')
,('24','영주','Yeongju','12.10','219.70')
,('25','풍기','Punggi','10.20','229.90')
,('26','단양','Danyang','16.98','246.88')
,('27','북단양','N.Danyang','14.19','261.07')
,('28','남제천','S.Jecheon','12.03','273.10')
,('28-1','제천분기점','JecheonJC','','')
,('29','제천','Jecheon','8.30','281.40')
,('30','신림','Sillim','12.82','294.22')
,('31','남원주','S.Wonju','18.58','312.80')
,('32','만종분기점','ManjongJC','4.95','317.75')
,('32-1','신평분기점','SinpyeongJC','','')
,('33','북원주','N.Wonju','7.78','325.53')
,('34','횡성','Hoengseong','10.71','336.24')
,('35','홍천','Hongcheon','25.59','361.83')
,('36','춘천분기점','ChuncheonJC','11.29','373.12')
,('37','춘천','Chuncheon','12.88','386.00');

update express_milestone
set road_type=0
whereroad_typeisnull;


update express_milestone
set keyword=concat(name,'ic');