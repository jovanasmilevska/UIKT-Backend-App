create or replace function create_exam_type_if_not_exists(_name text) returns void as
$$
begin

    if not exists(select id from public.exam_type where name = _name) then
        insert into public.exam_type(name)
        values (_name);
    end if;

end;

$$ language plpgsql;

select create_exam_type_if_not_exists('Прв'),
       create_exam_type_if_not_exists('Втор'),
       create_exam_type_if_not_exists('Испит');


