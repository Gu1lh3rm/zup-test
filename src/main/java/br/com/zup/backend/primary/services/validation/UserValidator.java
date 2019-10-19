package br.com.zup.backend.primary.services.validation;

import br.com.zup.backend.primary.domain.User;
import br.com.zup.backend.primary.domain.enums.UserType;
import br.com.zup.backend.primary.repositories.UserRepository;
import br.com.zup.backend.primary.resources.exceptions.FieldMessage;
import br.com.zup.backend.primary.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UserValidator implements ConstraintValidator<UserInput, User> {

   @Autowired
   private UserRepository repository;

   @Override
   public void initialize(UserInput ann) {
   }

   @Override
   public boolean isValid(User obj, ConstraintValidatorContext context) {
      List<FieldMessage> list = new ArrayList<>();
      if(repository != null) {
         Boolean validaSocialCodeExiste = Boolean.TRUE;
         Boolean validaEmailExiste = Boolean.TRUE;

         User auxSocialCode = repository.findBySocialCode(obj.getSocialCode());

         User aux = repository.findByEmail(obj.getEmail());

         if(aux != null) {
            if(aux.getEmail().equals(obj.getEmail()) && obj.getId() != null) {
               aux = null;
            }
         }
         if(auxSocialCode != null) {
            if(auxSocialCode.getSocialCode().equals(obj.getSocialCode()) && obj.getId() != null) {
               auxSocialCode = null;
            }
         }

         if(obj.getUserType() != null) {
            if (obj.getUserType().equals(UserType.PEOPLE.getCod()) && !BR.isValidCPF(obj.getSocialCode())) {
               list.add(new FieldMessage("socialCode", "Invalid CPF"));
            } else if (obj.getUserType().equals(UserType.LEGALPERSON.getCod()) && !BR.isValidCNPJ(obj.getSocialCode())) {
               list.add(new FieldMessage("socialCode", "Invalid CNPJ"));
            }

            if (auxSocialCode != null && obj.getUserType().equals(UserType.PEOPLE.getCod())) {
               list.add(new FieldMessage("socialCode", "Existing CPF"));
            }
            if (auxSocialCode != null && obj.getUserType().equals(UserType.LEGALPERSON.getCod())) {
               list.add(new FieldMessage("socialCode", "Existing CNPJ"));
            }
            if (aux != null) {
               list.add(new FieldMessage("email", "Existing  Email"));
            }
         }


         for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
         }
      }

      return list.isEmpty();
   }
}