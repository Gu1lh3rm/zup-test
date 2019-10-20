package br.com.zup.backend.primary.services.generics;

import br.com.zup.backend.primary.core.security.UserSS;
import br.com.zup.backend.primary.domain.common.Common;
import br.com.zup.backend.primary.domain.enums.Role;
import br.com.zup.backend.primary.repositories.generics.GenericRepository;
import br.com.zup.backend.primary.services.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/** Class implements common methods in all aplication but you need to extend Common class.
 * This class implements GenericServiceImpl and all Services the other repositories
 * @author Guilherme Lourenco
 * @version 1.00
 * @since Release 01 of the application
 */
public class GenericService<C extends Common> implements GenericServiceImpl<C> {

    @Autowired
    private BCryptPasswordEncoder encoder;

    private GenericRepository<C> repository;

    /** Received or service of type Type class.
     * @return ClassRepository Repository
     */
    public GenericService(GenericRepository<C> repository) {
        this.repository = repository;
    }

    /** Return List<Object> type the class.
     * @return ResponseEntity<List>
     */
    @Override
    public List<C> findAll() {
        return repository().findAll();
    }

    /** Return Object type the class
     * @param id Long
     * @return Object - type of class
     */
    @Override
    public C findById(Long id) {
        return findByIdObjectNotFound(id);
    }

    /** Return Object type the class
     * @param entity Type of Class
     * @return Object
     * @throws ObjectNotFoundException
     */
    @Override
    public C save(C entity) {
        entity.setId(null);
        repository().save(entity);
        return findByIdObjectNotFound(entity.getId());
    }

    /** Return Object type the class
     * @param id Long
     * @param entity Type of Class
     * @return Object
     * @throws ObjectNotFoundException
     */
    @Override
    public C update(Long id, C entity) {
        acceptedCriteria(id, entity);
        findByIdObjectNotFound(entity.getId());
        return repository().save(entity);
    }

    /** Return Object type the class
     * @param id Long
     * @param entity Type of Class
     * @return Object
     * @throws ObjectNotFoundException
     * @throws DataIntegrityException
     */
    @Override
    public C delete(Long id, C entity) {
        acceptedCriteria(id, entity);
        try{
            repository().delete(entity);
        } catch (DataIntegrityException ex) {
            throw new DataIntegrityException("Class is being used in more than one system table");
        }
        return null;
    }

    @Override
    public List<C> findAll(String key, String operation, String value) {
        isNotNull(key,"key");
        isNotNull(operation,"operation");
        isNotNull(value,"key");

        SearchSpecification<C> spec
                = new SearchSpecification(new SearchCriteria(key, operation, value));

        return repository().findAll(spec);
    }

    @Override
    public GenericRepository<C> repository() {
        return repository;
    }

    private void acceptedCriteria(Long id, C entity) {
        if(id.equals(entity.getId())) {
            findByIdObjectNotFound(entity.getId());
        } else {
            acceptedCriteriaException(id, entity);
        }
    }

    private C findByIdObjectNotFound(Long id) {
        return repository().findById(id).orElseThrow(() -> objectIdNotFound(id));
    }

    private ObjectNotFoundException objectIdNotFound(Long id) {
        return  new ObjectNotFoundException(
                "Object not found! Id: " + id);
    }

    private void acceptedCriteriaException(Long id, C entity) {
        throw new ObjectNotFoundException(
                "Objects must be the same! Url Id: " + id + " and Class Id: " + entity.getId());
    }

    public BCryptPasswordEncoder getEncoder() {
        return encoder;
    }

    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    /** Validates if Object is not null
     * @param obj Object
     * @param type String
     * @throws ValidatorException
     */
    public void isNotNull(Object obj, String type) {
        if(obj == null) {
            throw new ValidatorException(type + " cannot be null");
        }

    }

    /** Get the logged in user
     * @return UserSS
     */
    public static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e){
            return null;
        }
    }

    /** Checks if the logged in user id is the same
     * @param id Long
     * @throws AuthorizationException
     */
    public void verifyUserSS(Long id) {

        UserSS userSS = authenticated();
        if(userSS.getId() == null || !userSS.hasRole(Role.ADMIN) && !id.equals(userSS.getId())){
            throw new AuthorizationException("Access deny");
        }

    }

    public void verifySpecificationException(String params, String key) {
        if(!(params.contains(key))) {
            throw new SpecificationException("Invalid Specification "+key);
        }
    }

}
