package com.robotandpencils.robotandpencilapp.services.di;

import android.app.Application;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class RPModule_ProvideApplicationFactory implements Factory<Application> {
  private final RPModule module;

  public RPModule_ProvideApplicationFactory(RPModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public Application get() {  
    Application provided = module.provideApplication();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<Application> create(RPModule module) {  
    return new RPModule_ProvideApplicationFactory(module);
  }
}

