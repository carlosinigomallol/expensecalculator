CREATE TABLE hmovimiento ( 
	sequence_name         	varchar(255) NOT NULL,
	sequence_next_hi_value	int(11) NULL 
	)
GO
CREATE TABLE hmovimientoConIva ( 
	sequence_name         	varchar(255) NOT NULL,
	sequence_next_hi_value	int(11) NULL 
	)
GO
CREATE TABLE hmovimientoConIvaPlan ( 
	sequence_name         	varchar(255) NOT NULL,
	sequence_next_hi_value	int(11) NULL 
	)
GO
CREATE TABLE hmovimientoHipotecaPlan ( 
	sequence_name         	varchar(255) NOT NULL,
	sequence_next_hi_value	int(11) NULL 
	)
GO
CREATE TABLE hmovimientoPeriodico ( 
	sequence_name         	varchar(255) NOT NULL,
	sequence_next_hi_value	int(11) NULL 
	)
GO
CREATE TABLE hmovimientoPeriodicoPlan ( 
	sequence_name         	varchar(255) NOT NULL,
	sequence_next_hi_value	int(11) NULL 
	)
GO
CREATE TABLE hmovimientoPlan ( 
	sequence_name         	varchar(255) NOT NULL,
	sequence_next_hi_value	int(11) NULL 
	)
GO
CREATE TABLE hmovimientoRentingPlan ( 
	sequence_name         	varchar(255) NOT NULL,
	sequence_next_hi_value	int(11) NULL 
	)
GO
CREATE TABLE hpInterMovAPP ( 
	sequence_name         	varchar(255) NOT NULL,
	sequence_next_hi_value	int(11) NULL 
	)
GO
CREATE TABLE hpInterMovCIP ( 
	sequence_name         	varchar(255) NOT NULL,
	sequence_next_hi_value	int(11) NULL 
	)
GO
CREATE TABLE hpInterMovHP ( 
	sequence_name         	varchar(255) NOT NULL,
	sequence_next_hi_value	int(11) NULL 
	)
GO
CREATE TABLE hpInterMovP ( 
	sequence_name         	varchar(255) NOT NULL,
	sequence_next_hi_value	int(11) NULL 
	)
GO
CREATE TABLE hpInterMovRP ( 
	sequence_name         	varchar(255) NOT NULL,
	sequence_next_hi_value	int(11) NULL 
	)
GO
CREATE TABLE hplan ( 
	sequence_name         	varchar(255) NOT NULL,
	sequence_next_hi_value	int(11) NULL 
	)
GO
CREATE TABLE husuario ( 
	sequence_name         	varchar(255) NOT NULL,
	sequence_next_hi_value	int(11) NULL 
	)
GO
CREATE TABLE movimiento ( 
	id             	decimal(18,0) NOT NULL,
	descripcion    	varchar(255) NOT NULL,
	totalMovimiento	decimal(18,2) NOT NULL,
	divisa         	varchar(3) NOT NULL,
	fechaInicio    	date NOT NULL,
	fechaFin       	date NOT NULL,
	tipoMovimiento 	varchar(2) NULL,
	signoMovimiento	varchar(2) NOT NULL,
	usuarioid      	decimal(18,0) NOT NULL 
	)
GO
CREATE TABLE movimientoAPlazosPlan ( 
	id                   	decimal(18,0) NOT NULL,
	fechaInicio          	date NOT NULL,
	fechaFin             	date NOT NULL,
	descripcionMovimiento	varchar(255) NOT NULL,
	divisa               	varchar(3) NOT NULL,
	iva                  	decimal(18,2) NULL,
	numeroAnos           	decimal(18,2) NULL,
	importeAFinanciar    	decimal(18,2) NULL,
	comisionApertura     	decimal(18,2) NULL,
	tipoInteresNominal   	decimal(18,2) NULL,
	tasaAnual            	decimal(18,2) NULL,
	totalMeses           	decimal(18,2) NULL,
	totalInteresesAPagar 	decimal(18,2) NULL,
	usuarioid            	decimal(18,0) NULL 
	)
GO
CREATE TABLE movimientoConIva ( 
	id               	decimal(18,0) NOT NULL,
	descripcion      	varchar(255) NOT NULL,
	totalMovimiento  	decimal(18,2) NOT NULL,
	divisa           	varchar(3) NOT NULL,
	fechaInicio      	date NOT NULL,
	fechaFin         	date NOT NULL,
	tipoMovimiento   	varchar(2) NULL,
	signoMovimiento  	varchar(2) NOT NULL,
	iva              	decimal(18,2) NULL,
	numeroArticulos  	decimal(18,2) NULL,
	cantidadPorUnidad	decimal(18,2) NULL,
	usuarioid        	decimal(18,0) NOT NULL 
	)
GO
CREATE TABLE movimientoConIvaPlan ( 
	id               	decimal(18,0) NOT NULL,
	descripcion      	varchar(255) NOT NULL,
	totalMovimiento  	decimal(18,2) NOT NULL,
	divisa           	varchar(3) NOT NULL,
	fechaInicio      	date NOT NULL,
	fechaFin         	date NOT NULL,
	tipoMovimiento   	varchar(2) NULL,
	signoMovimiento  	varchar(2) NOT NULL,
	iva              	decimal(18,2) NULL,
	numeroArticulos  	decimal(18,2) NULL,
	cantidadPorUnidad	decimal(18,2) NULL,
	usuarioid        	decimal(18,0) NULL 
	)
GO
CREATE TABLE movimientoHipotecaPlan ( 
	id                   	decimal(18,0) NOT NULL,
	fechaInicio          	date NOT NULL,
	fechaFin             	date NOT NULL,
	descripcionMovimiento	varchar(255) NOT NULL,
	divisa               	varchar(3) NOT NULL,
	interes              	decimal(18,2) NULL,
	cuota                	decimal(18,2) NULL,
	capital              	decimal(18,2) NULL,
	plazo                	decimal(18,2) NULL,
	usuarioid            	decimal(18,0) NULL 
	)
GO
CREATE TABLE movimientoPeriodico ( 
	id                 	decimal(18,0) NOT NULL,
	descripcion        	varchar(255) NOT NULL,
	divisa             	varchar(3) NOT NULL,
	periodidadEjecucion	varchar(2) NOT NULL,
	diaEjecucion       	decimal(18,2) NOT NULL,
	cantidad           	decimal(18,2) NOT NULL,
	fechaInicio        	date NULL,
	fechaFin           	date NULL,
	tipoMovimiento     	varchar(2) NULL,
	signoMovimiento    	varchar(2) NOT NULL,
	usuarioid          	decimal(18,0) NOT NULL 
	)
GO
CREATE TABLE movimientoPeriodicoPlan ( 
	id                 	decimal(18,0) NOT NULL,
	descripcion        	varchar(255) NOT NULL,
	divisa             	varchar(3) NOT NULL,
	periodidadEjecucion	varchar(2) NOT NULL,
	diaEjecucion       	decimal(18,2) NOT NULL,
	cantidad           	decimal(18,2) NOT NULL,
	fechaInicio        	date NULL,
	fechaFin           	date NULL,
	tipoMovimiento     	varchar(2) NULL,
	signoMovimiento    	varchar(2) NOT NULL,
	usuarioid          	decimal(18,0) NOT NULL 
	)
GO
CREATE TABLE movimientoPlan ( 
	id             	decimal(18,0) NOT NULL,
	descripcion    	varchar(255) NOT NULL,
	totalMovimiento	decimal(18,2) NOT NULL,
	divisa         	varchar(3) NOT NULL,
	fechaInicio    	date NOT NULL,
	fechaFin       	date NOT NULL,
	tipoMovimiento 	varchar(2) NULL,
	signoMovimiento	varchar(2) NOT NULL,
	usuarioid      	decimal(18,0) NOT NULL 
	)
GO
CREATE TABLE movimientoRentingPlan ( 
	id                   	decimal(18,0) NOT NULL,
	descripcionMovimiento	varchar(255) NOT NULL,
	cantidadMensual      	decimal(18,2) NOT NULL,
	divisa               	varchar(3) NOT NULL,
	fechaInicio          	date NOT NULL,
	fechaFin             	date NOT NULL,
	numeroAnos           	decimal(18,2) NULL,
	usuarioid            	decimal(18,0) NULL 
	)
GO
CREATE TABLE pInterMovAPP ( 
	id                     	decimal(18,0) NOT NULL,
	planid                 	decimal(18,0) NOT NULL,
	movimientoAPlazosPlanid	decimal(18,0) NOT NULL 
	)
GO
CREATE TABLE pInterMovCIP ( 
	id                    	decimal(18,0) NOT NULL,
	planid                	decimal(18,0) NOT NULL,
	movimientoConIvaPlanid	decimal(18,0) NOT NULL 
	)
GO
CREATE TABLE pInterMovHP ( 
	id                      	decimal(18,0) NOT NULL,
	planid                  	decimal(18,0) NOT NULL,
	movimientoHipotecaPlanid	decimal(18,0) NOT NULL 
	)
GO
CREATE TABLE pInterMovP ( 
	id              	decimal(18,0) NOT NULL,
	planid          	decimal(18,0) NOT NULL,
	movimientoPlanid	decimal(18,0) NOT NULL 
	)
GO
CREATE TABLE pInterMovPP ( 
	id                       	decimal(18,0) NOT NULL,
	planid                   	decimal(18,0) NOT NULL,
	movimientoPeriodicoPlanid	decimal(18,0) NOT NULL 
	)
GO
CREATE TABLE pInterMovRP ( 
	id                     	decimal(18,0) NOT NULL,
	planid                 	decimal(18,0) NOT NULL,
	movimientoRentingPlanid	decimal(18,0) NOT NULL 
	)
GO
CREATE TABLE plan ( 
	id             	decimal(18,0) NOT NULL,
	descripcionPlan	varchar(255) NOT NULL,
	usuarioid      	decimal(18,0) NOT NULL 
	)
GO
CREATE TABLE planfuturo ( 
	id                       	decimal(18,0) NOT NULL,
	planid                   	decimal(18,0) NOT NULL,
	movimientoid             	decimal(18,0) NULL,
	movimientoplanid         	decimal(18,0) NULL,
	movimientoperiodicoplanid	decimal(18,0) NULL 
	)
GO
CREATE TABLE usuario ( 
	id      	decimal(18,0) NOT NULL,
	name    	varchar(100) NOT NULL,
	login   	varchar(50) NOT NULL,
	password	varchar(50) NOT NULL 
	)
GO

ALTER TABLE hmovimientoHipotecaPlan
	ADD CONSTRAINT PRIMARY
	PRIMARY KEY (sequence_name)
GO
ALTER TABLE hmovimientoPeriodico
	ADD CONSTRAINT PRIMARY
	PRIMARY KEY (sequence_name)
GO
ALTER TABLE hmovimientoPeriodicoPlan
	ADD CONSTRAINT PRIMARY
	PRIMARY KEY (sequence_name)
GO
ALTER TABLE hmovimientoPlan
	ADD CONSTRAINT PRIMARY
	PRIMARY KEY (sequence_name)
GO
ALTER TABLE hmovimientoRentingPlan
	ADD CONSTRAINT PRIMARY
	PRIMARY KEY (sequence_name)
GO
ALTER TABLE hpInterMovAPP
	ADD CONSTRAINT PRIMARY
	PRIMARY KEY (sequence_name)
GO
ALTER TABLE hpInterMovCIP
	ADD CONSTRAINT PRIMARY
	PRIMARY KEY (sequence_name)
GO
ALTER TABLE hpInterMovHP
	ADD CONSTRAINT PRIMARY
	PRIMARY KEY (sequence_name)
GO
ALTER TABLE hpInterMovP
	ADD CONSTRAINT PRIMARY
	PRIMARY KEY (sequence_name)
GO
ALTER TABLE hpInterMovRP
	ADD CONSTRAINT PRIMARY
	PRIMARY KEY (sequence_name)
GO
ALTER TABLE movimiento
	ADD CONSTRAINT PRIMARY
	PRIMARY KEY (id)
GO
ALTER TABLE movimientoAPlazosPlan
	ADD CONSTRAINT PRIMARY
	PRIMARY KEY (id)
GO
ALTER TABLE movimientoConIva
	ADD CONSTRAINT PRIMARY
	PRIMARY KEY (id)
GO
ALTER TABLE movimientoConIvaPlan
	ADD CONSTRAINT PRIMARY
	PRIMARY KEY (id)
GO
ALTER TABLE movimientoHipotecaPlan
	ADD CONSTRAINT PRIMARY
	PRIMARY KEY (id)
GO
ALTER TABLE movimientoPeriodico
	ADD CONSTRAINT PRIMARY
	PRIMARY KEY (id)
GO
ALTER TABLE movimientoPeriodicoPlan
	ADD CONSTRAINT PRIMARY
	PRIMARY KEY (id)
GO
ALTER TABLE movimientoPlan
	ADD CONSTRAINT PRIMARY
	PRIMARY KEY (id)
GO
ALTER TABLE movimientoRentingPlan
	ADD CONSTRAINT PRIMARY
	PRIMARY KEY (id)
GO
ALTER TABLE plan
	ADD CONSTRAINT PRIMARY
	PRIMARY KEY (id)
GO
ALTER TABLE planfuturo
	ADD CONSTRAINT PRIMARY
	PRIMARY KEY (id)
GO
ALTER TABLE usuario
	ADD CONSTRAINT PRIMARY
	PRIMARY KEY (id)
GO
ALTER TABLE movimiento
	ADD CONSTRAINT fk_k13
	FOREIGN KEY(usuarioid)
	REFERENCES usuario(id)
GO
ALTER TABLE movimientoAPlazosPlan
	ADD CONSTRAINT fk_k21
	FOREIGN KEY(usuarioid)
	REFERENCES usuario(id)
GO
ALTER TABLE movimientoConIva
	ADD CONSTRAINT fk_k14
	FOREIGN KEY(usuarioid)
	REFERENCES usuario(id)
GO
ALTER TABLE movimientoConIvaPlan
	ADD CONSTRAINT fk_k17
	FOREIGN KEY(usuarioid)
	REFERENCES usuario(id)
GO
ALTER TABLE movimientoHipotecaPlan
	ADD CONSTRAINT fk_k20
	FOREIGN KEY(usuarioid)
	REFERENCES usuario(id)
GO
ALTER TABLE movimientoPeriodico
	ADD CONSTRAINT fk_k15
	FOREIGN KEY(usuarioid)
	REFERENCES usuario(id)
GO
ALTER TABLE movimientoPeriodicoPlan
	ADD CONSTRAINT fk_k18
	FOREIGN KEY(usuarioid)
	REFERENCES usuario(id)
GO
ALTER TABLE movimientoPlan
	ADD CONSTRAINT fk_k16
	FOREIGN KEY(usuarioid)
	REFERENCES usuario(id)
GO
ALTER TABLE movimientoRentingPlan
	ADD CONSTRAINT fk_k19
	FOREIGN KEY(usuarioid)
	REFERENCES usuario(id)
GO
ALTER TABLE pInterMovAPP
	ADD CONSTRAINT fk_k9
	FOREIGN KEY(planid)
	REFERENCES plan(id)
GO
ALTER TABLE pInterMovAPP
	ADD CONSTRAINT fk_k10
	FOREIGN KEY(movimientoAPlazosPlanid)
	REFERENCES movimientoAPlazosPlan(id)
GO
ALTER TABLE pInterMovCIP
	ADD CONSTRAINT fk_k8
	FOREIGN KEY(movimientoConIvaPlanid)
	REFERENCES movimientoConIvaPlan(id)
GO
ALTER TABLE pInterMovCIP
	ADD CONSTRAINT fk_k7
	FOREIGN KEY(planid)
	REFERENCES plan(id)
GO
ALTER TABLE pInterMovHP
	ADD CONSTRAINT fk_k6
	FOREIGN KEY(movimientoHipotecaPlanid)
	REFERENCES movimientoHipotecaPlan(id)
GO
ALTER TABLE pInterMovHP
	ADD CONSTRAINT fk_k5
	FOREIGN KEY(planid)
	REFERENCES plan(id)
GO
ALTER TABLE pInterMovP
	ADD CONSTRAINT fk_k4
	FOREIGN KEY(movimientoPlanid)
	REFERENCES movimientoPlan(id)
GO
ALTER TABLE pInterMovP
	ADD CONSTRAINT fk_k3
	FOREIGN KEY(planid)
	REFERENCES plan(id)
GO
ALTER TABLE pInterMovPP
	ADD CONSTRAINT fk_k12
	FOREIGN KEY(movimientoPeriodicoPlanid)
	REFERENCES movimientoPeriodicoPlan(id)
GO
ALTER TABLE pInterMovPP
	ADD CONSTRAINT fk_k11
	FOREIGN KEY(planid)
	REFERENCES plan(id)
GO
ALTER TABLE pInterMovRP
	ADD CONSTRAINT fk_k2
	FOREIGN KEY(movimientoRentingPlanid)
	REFERENCES movimientoRentingPlan(id)
GO
ALTER TABLE pInterMovRP
	ADD CONSTRAINT fk_k1
	FOREIGN KEY(planid)
	REFERENCES plan(id)
GO
ALTER TABLE planfuturo
	ADD CONSTRAINT fk_p
	FOREIGN KEY(planid)
	REFERENCES plan(id)
GO
ALTER TABLE planfuturo
	ADD CONSTRAINT fk_mpp
	FOREIGN KEY(movimientoperiodicoplanid)
	REFERENCES movimientoPeriodicoPlan(id)
GO
ALTER TABLE planfuturo
	ADD CONSTRAINT fk_mp
	FOREIGN KEY(movimientoplanid)
	REFERENCES movimientoPlan(id)
GO
ALTER TABLE planfuturo
	ADD CONSTRAINT fk_m
	FOREIGN KEY(movimientoid)
	REFERENCES movimiento(id)
GO