create or replace function create_semester_type_if_not_exists(_name text) returns void as
$$
begin

    if not exists(select id from public.semester_type where name = _name) then
        insert into public.semester_type(name)
        values (_name);
    end if;

end;

$$ language plpgsql;

select create_semester_type_if_not_exists('Зимски');
select create_semester_type_if_not_exists('Летен');



