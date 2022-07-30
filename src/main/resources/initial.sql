-- INSERT ROLES
INSERT INTO public.roles(
	id, created_by, created_date, modified_by, modified_date, status, code, name)
	VALUES (1, 'admin', now(), 'admin', now(), true, 'ROLE_ADMIN', 'Người quản trị');
INSERT INTO public.roles(
	id, created_by, created_date, modified_by, modified_date, status, code, name)
	VALUES (2, 'admin', now(), 'admin', now(), true, 'ROLE_MANAGER', 'Ban giám hiệu');
INSERT INTO public.roles(
	id, created_by, created_date, modified_by, modified_date, status, code, name)
	VALUES (3, 'admin', now(), 'admin', now(), true, 'ROLE_TEACHER', 'Giáo viên');
INSERT INTO public.roles(
	id, created_by, created_date, modified_by, modified_date, status, code, name)
	VALUES (4, 'admin', now(), 'admin', now(), true, 'ROLE_STUDENT', 'Sinh viên');
    
-- INSERT ADMIN
INSERT INTO public.teacher(
	id, created_by, created_date, modified_by, modified_date, status, address, avatar, dob, email, gender, name, password, phone, user_id, is_head_of_faculty, is_manager)
	VALUES (1, 'admin', now(), 'admin', now(), true, '', '', now(), '', true, 'admin', '$2a$10$U1OCMrT1vZVvA8.yOyufueI7mFJViyYuwWqMhickyoI1CbLTIJbbG', '', 'admin', false, false);
    
-- INSERT ROLE_TEACHER 
INSERT INTO public.role_teacher(
	teacher_id, role_id)
	VALUES (1, 1);
INSERT INTO public.role_teacher(
	teacher_id, role_id)
	VALUES (1, 3);