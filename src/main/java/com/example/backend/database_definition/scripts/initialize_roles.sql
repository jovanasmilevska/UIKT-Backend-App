create or replace function create_role_if_not_exists(_name text) returns void as
$$
begin

    if not exists(select id from auth_user.roles where name = _name) then
        insert into auth_user.roles(name)
        values (_name);
    end if;

end;
$$ language plpgsql;

select create_role_if_not_exists('ROLE_ADMIN'),
       create_role_if_not_exists('ROLE_USER');


