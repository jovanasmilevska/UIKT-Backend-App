create or replace function create_year_if_not_exists(_name text) returns void as
$$
begin

    if not exists(select id from public.year where name = _name) then
        insert into public.year(name)
        values (_name);
    end if;

end;

$$ language plpgsql;

select create_year_if_not_exists('Прва');
select create_year_if_not_exists('Втора');
select create_year_if_not_exists('Трета');
select create_year_if_not_exists('Четврта');


