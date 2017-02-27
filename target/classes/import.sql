insert into service (id, name, description, bindable) values ('S123', 'hashbroker123', 'Hashmap as a Service', true);
insert into plan (id, name, description, service_id) values ('P123', 'hashPlan123', 'Plan for hashbroker123 service', 'S123');
