# PricingModule

Steps For Execution :

1. Clone the project from GitHub : https://github.com/sonu91social/PricingModule
2. Build The Project After Cloning From CommandLine :
       Using : gradle clean build -x test
3. Start The Spring Boot Project : Go to Build/libs folder
       and there from cmd : run command :- java -jar pricing-0.0.1-SNAPSHOT.jar
4. Create A DB in any MySQL Client : Name (pricingmodule)
5. Run the Following Script In mysql Client :

	INSERT INTO manufacturer(company_name)
	VALUE ("HeroCycles");

	INSERT INTO frame_categories(frame_type,frame_price,frame_id)
	VALUES ("Steel",800,1),("aluminium",1100,1),("titanium",1350,1);

	INSERT INTO brake_categories(brake_type,brake_price,brake_id)
	VALUES ("Rim",300,1),("Disk",700,1),("gear",1000,1);

	INSERT INTO chain_categories(chain_type,chain_price,chain_id)
	VALUES ("one-Speed",600,1),("Derailled-Chains",1000,1);

	INSERT INTO wheel_categories(wheel_type,wheel_price,wheel_id)
	VALUES ("spokes",300,1),("rim",400,1),("tube",600,1),("tyre",500,1);

	INSERT INTO seat_categories(seat_type,seat_price,seat_id)
	VALUES ("racing-saddle",300,1),("mountain-saddle",500,1),("gel-saddle",600,1);
	
	INSERT INTO module_pices_variation_per_year(percentage_increase,YEAR)
	VALUES (5,"2021"),(10,"2022"),(12,2023),(0,2020);
	
6.  Now open another cmd : and run following command
    curl -v localhost:8000/getCycle/price/steel/gear/mountain-saddle/tube/Derailled-Chains/2020
    




